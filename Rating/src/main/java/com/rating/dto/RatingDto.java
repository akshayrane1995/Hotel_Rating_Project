package com.rating.dto;

public record RatingDto(Long id, Long userId, Long hotelId, Long rating, String feedback) {

}
