package com.rating.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rating.dto.HotelDto;


@FeignClient(name = "hotel-service" , url = "${hotel.service.url}")
public interface HotelServiceClient {
	
	@GetMapping("/hotel/id/{id}")
	HotelDto getHotelById(@PathVariable Long id);

}
