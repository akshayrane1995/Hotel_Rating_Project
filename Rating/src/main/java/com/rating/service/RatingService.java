package com.rating.service;

import java.util.List;

import com.rating.dto.RatingDto;

public interface RatingService {

	RatingDto createRating(RatingDto ratingDto);

	RatingDto getRatingById(Long id);

	List<RatingDto> getAllRatings();

	RatingDto updateRating(Long id, RatingDto ratingDto);

	void deleteRating(Long id);

}
