package com.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.hotel.dto.HotelDto;
import com.hotel.entity.Hotel;
import com.hotel.exception.ResourceNotFoundException;
import com.hotel.mapper.HotelMapper;
import com.hotel.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {

	
	private HotelRepository hotelRepository;

	public HotelServiceImpl(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}

	@Override
	public HotelDto createHotel(HotelDto hotelDto) {
		Hotel hotel = HotelMapper.mapToHotel(hotelDto);
		Hotel saveHotel = hotelRepository.save(hotel);
		return HotelMapper.mapToHotelDto(saveHotel);
	}

	@Override
	public HotelDto getHotelById(Long id) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel does not exist"));
		return HotelMapper.mapToHotelDto(hotel);
	}

	@Override
	public List<HotelDto> getAllHotels() {
		List<Hotel> hotels = hotelRepository.findAll();
		return hotels.stream().map((hotel) -> HotelMapper.mapToHotelDto(hotel)).collect(Collectors.toList());
	}

	@Override
	public HotelDto updateHotel(Long id , HotelDto hotelDto) {
		Hotel hotel = hotelRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Hotel does not exist"));

		hotel.setName(hotelDto.name());
		hotel.setCity(hotelDto.city());

		Hotel updatedhotel = hotelRepository.save(hotel);
		return HotelMapper.mapToHotelDto(updatedhotel);

	}

	@Override
	public void deleteHotel(Long id) {
		if (!hotelRepository.existsById(id)) {
			throw new ResourceNotFoundException("Hotel does not exist");
		}
		hotelRepository.deleteById(id);
	}

}
