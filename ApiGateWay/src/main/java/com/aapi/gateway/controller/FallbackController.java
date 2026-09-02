package com.aapi.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@RequestMapping("/fallback/hotel")
	public ResponseEntity<String> hotelServiceFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("Hotel service is currently unavailable, please try again later");
	}

	@RequestMapping("/fallback/user")
	public ResponseEntity<String> userServiceFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("User service is currently unavailable, please try again later");
	}

	@RequestMapping("/fallback/rating")
	public ResponseEntity<String> ratingServiceFallback() {
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
				.body("Rating service is currently unavailable, please try again later");
	}
}