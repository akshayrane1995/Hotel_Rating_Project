package com.rating.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RatingDto(Long id, 
						
						@NotNull(message = "User id is required")
						Long userId, 
						
						@NotNull(message = "Hotel id is required")
						Long hotelId, 
						
						@NotNull(message = "Rating is required")
				        @Min(value = 1, message = "Rating must be at least 1")
				        @Max(value = 5, message = "Rating must not exceed 5")
						Long rating, 
						
						@NotBlank(message = "Feedback is required")
						String feedback) {

}
