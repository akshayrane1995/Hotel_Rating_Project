package com.rating.service;

import java.util.List;


import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.rating.client.HotelServiceClient;
import com.rating.client.UserServiceClient;
import com.rating.dto.RatingDto;
import com.rating.entity.Rating;
import com.rating.exception.RatingAlreadyExistsException;
import com.rating.exception.ResourceNotFoundException;
import com.rating.mapper.RatingMapper;
import com.rating.repository.RatingRepository;

import feign.FeignException;

@Service
public class RatingServiceImpl implements RatingService {

	private RatingRepository ratingRepository;
	private HotelServiceClient hotelServiceClient;
	private UserServiceClient userServiceClient;

	public RatingServiceImpl(RatingRepository ratingRepository, HotelServiceClient hotelServiceClient,
			UserServiceClient userServiceClient) {
		this.ratingRepository = ratingRepository;
		this.hotelServiceClient = hotelServiceClient;
		this.userServiceClient = userServiceClient;
	}

	@Override
	public RatingDto createRating(RatingDto ratingDto) {

		validateHotel(ratingDto.hotelId());
		validateUser(ratingDto.userId());

		boolean alreadyRated = ratingRepository.existsByUserIdAndHotelId(ratingDto.userId(), ratingDto.hotelId());

		if (alreadyRated) {
			throw new RatingAlreadyExistsException("User has already rated this hotel");
		}

		Rating rating = RatingMapper.mapToRating(ratingDto);
		Rating saveRating = ratingRepository.save(rating);
		return RatingMapper.mapToRatingDto(saveRating);

	}

	@Override
	public RatingDto getRatingById(Long id) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rating does not exist"));
		return RatingMapper.mapToRatingDto(rating);
	}

	@Override
	public List<RatingDto> getAllRatings() {
		List<Rating> ratings = ratingRepository.findAll();
		return ratings.stream().map((rating) -> RatingMapper.mapToRatingDto(rating)).collect(Collectors.toList());
	}

	@Override
	public RatingDto updateRating(Long id, RatingDto ratingDto) {
		Rating rating = ratingRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rating does not exist"));

		validateHotel(ratingDto.hotelId());
		validateUser(ratingDto.userId());

		rating.setRating(ratingDto.rating());
		rating.setFeedback(ratingDto.feedback());

		Rating updatedrating = ratingRepository.save(rating);
		return RatingMapper.mapToRatingDto(updatedrating);

	}

	@Override
	public void deleteRating(Long id) {
		if (!ratingRepository.existsById(id)) {
			throw new ResourceNotFoundException("Rating does not exist");
		}
		ratingRepository.deleteById(id);
	}

	private void validateHotel(Long hotelId) {
		try {
			hotelServiceClient.getHotelById(hotelId);
		} catch (FeignException.NotFound e) {
			throw new ResourceNotFoundException("Hotel does not exist with id: " + hotelId);
		}
	}

	private void validateUser(Long userId) {
		try {
			userServiceClient.getUserById(userId);
		} catch (FeignException.NotFound e) {
			throw new ResourceNotFoundException("User does not exist with id: " + userId);
		}
	}
}
