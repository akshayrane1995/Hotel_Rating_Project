package com.rating.service;

import org.springframework.stereotype.Service;
import com.rating.client.HotelServiceClient;
import com.rating.client.UserServiceClient;
import com.rating.exception.ResourceNotFoundException;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class ServiceValidation {

	private final HotelServiceClient hotelServiceClient;
	private UserServiceClient userServiceClient;

	public ServiceValidation(HotelServiceClient hotelServiceClient,UserServiceClient userServiceClient) {
		this.hotelServiceClient = hotelServiceClient;
		this.userServiceClient = userServiceClient;
	}

	@CircuitBreaker(name = "hotelService", fallbackMethod = "hotelServiceFallback")
	public void validateHotel(Long hotelId) {
		try {
			hotelServiceClient.getHotelById(hotelId);
		} catch (FeignException.NotFound e) {
			throw new ResourceNotFoundException("Hotel does not exist with id: " + hotelId);
		}
	}

	@SuppressWarnings("unused")
	private void hotelServiceFallback(Long hotelId, Throwable ex) {
		throw new RuntimeException("Hotel service is currently unavailable");
	}
	
	@CircuitBreaker(name = "userService", fallbackMethod = "userServiceFallback")
	public void validateUser(Long userId) {
		try {
			userServiceClient.getUserById(userId);
		} catch (FeignException.NotFound e) {
			throw new ResourceNotFoundException("User does not exist with id: " + userId);
		}
	}
	
	private void userServiceFallback(Long userId, Throwable  ex) {
		throw new RuntimeException("User service is currently unavailable");
	}

}
