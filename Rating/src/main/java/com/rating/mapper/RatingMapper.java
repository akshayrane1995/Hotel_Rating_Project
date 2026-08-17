package com.rating.mapper;

import com.rating.dto.RatingDto;
import com.rating.entity.Rating;

public class RatingMapper {

	public static Rating mapToRating(RatingDto ratingDto) {
		Rating rating = new Rating(
				ratingDto.id(),
				ratingDto.userId(),
				ratingDto.hotelId(),
				ratingDto.rating(),
				ratingDto.feedback()
				);
		return rating;
	}
	
	public static RatingDto mapToRatingDto(Rating rating) {
		RatingDto ratingDto = new RatingDto(
				rating.getId(),
				rating.getUserId(),
				rating.getHotelId(),
				rating.getRating(),
				rating.getFeedback()
				);
		
		return ratingDto;
	}
}