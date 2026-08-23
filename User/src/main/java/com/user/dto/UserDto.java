package com.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(Long id, 
					@NotBlank(message = "Name is required")
					String name,
					

			        @NotBlank(message = "Email is required")
			        @Email(message = "Please enter a valid email")
					String email) {

}
