package com.hotel.dto;

import jakarta.validation.constraints.NotBlank;

public record HotelDto(Long id, 
						
						@NotBlank(message = "Hotel name is required") 
						String name,
						
						@NotBlank(message = "City is required")
						String city) {

}
