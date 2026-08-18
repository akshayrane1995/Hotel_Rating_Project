package com.rating.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.rating.client.HotelServiceClient;
import com.rating.dto.HotelDto;
import com.rating.dto.RatingDto;
import com.rating.entity.Rating;
import com.rating.exception.ResourceNotFoundException;
import com.rating.mapper.RatingMapper;
import com.rating.repository.RatingRepository;

import feign.FeignException;

@Service
public class RatingServiceImpl implements RatingService {

	private RatingRepository ratingRepository;
	private HotelServiceClient hotelServiceClient;

	public RatingServiceImpl(RatingRepository ratingRepository, HotelServiceClient hotelServiceClient) {
		this.ratingRepository = ratingRepository;
		this.hotelServiceClient = hotelServiceClient;
	}

	@Override
	public RatingDto createRating(RatingDto ratingDto) {
		try {
			HotelDto hotel = hotelServiceClient.getHotelById(ratingDto.hotelId());
		} catch (FeignException.NotFound e) {
			throw new ResourceNotFoundException("Hotel does not exist with id:" + ratingDto.hotelId());
		}
		Rating rating = RatingMapper.mapToRating(ratingDto);
		Rating saveRating = ratingRepository.save(rating);
		return RatingMapper.mapToRatingDto(saveRating);

	}

	@Override
	public RatingDto getRatingById(Long id) {
		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating does not exist"));
		return RatingMapper.mapToRatingDto(rating);
	}

	@Override
	public List<RatingDto> getAllRatings() {
		List<Rating> ratings = ratingRepository.findAll();
		return ratings.stream().map((rating) -> RatingMapper.mapToRatingDto(rating)).collect(Collectors.toList());
	}

	@Override
	public RatingDto updateRating(Long id, RatingDto ratingDto) {
		Rating rating = ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating does not exist"));

		rating.setRating(ratingDto.rating());
		rating.setUserId(ratingDto.userId());
		rating.setHotelId(ratingDto.hotelId());
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

}
