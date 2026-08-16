package com.hotel.service;

import java.util.List;

import com.hotel.dto.HotelDto;

public interface HotelService {

	HotelDto createHotel(HotelDto hotelDto);

	HotelDto getHotelById(Long id);

	List<HotelDto> getAllHotels();

	void deleteHotel(Long id);

	HotelDto updateHotel(Long id , HotelDto hotelDto);

	}
