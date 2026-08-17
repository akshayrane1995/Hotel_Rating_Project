package com.rating.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rating.dto.RatingDto;
import com.rating.entity.Rating;
import com.rating.mapper.RatingMapper;
import com.rating.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

	private RatingRepository ratingRepository;

	public RatingServiceImpl(RatingRepository ratingRepository) {
		this.ratingRepository = ratingRepository;
	}

	@Override
	public RatingDto createRating(RatingDto ratingDto) {
		Rating rating = RatingMapper.mapToRating(ratingDto);
		Rating saveRating = ratingRepository.save(rating);
		return RatingMapper.mapToRatingDto(saveRating);
	}

	@Override
	public RatingDto getRatingById(Long id) {
		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating does not exist"));
		return RatingMapper.mapToRatingDto(rating);
	}

	@Override
	public List<RatingDto> getAllRatings() {
		List<Rating> ratings = ratingRepository.findAll();
		return ratings.stream().map((rating) -> RatingMapper.mapToRatingDto(rating)).collect(Collectors.toList());
	}

	@Override
	public RatingDto updateRating(Long id, RatingDto ratingDto) {
		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating does not exist"));

		rating.setRating(ratingDto.id());
		rating.setUserId(ratingDto.userId());
		rating.setHotelId(ratingDto.hotelId());
		rating.setFeedback(ratingDto.feedback());

		Rating updatedrating = ratingRepository.save(rating);
		return RatingMapper.mapToRatingDto(updatedrating);

	}

	@Override
	public void deleteRating(Long id) {
		if (!ratingRepository.existsById(id)) {
			throw new RuntimeException("Rating does not exist");
		}
		ratingRepository.deleteById(id);
	}

}
