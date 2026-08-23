package com.hotel.mapper;

import com.hotel.dto.HotelDto;
import com.hotel.entity.Hotel;

public class HotelMapper {

	public static Hotel mapToHotel(HotelDto hotelDto){
		  Hotel hotel = new Hotel(
				  hotelDto.id(),
				  hotelDto.name(),
				  hotelDto.city()
				  );
		  return hotel;
	}
	
	
	public static HotelDto mapToHotelDto(Hotel hotel){
		HotelDto hotelDto = new HotelDto(
				hotel.getId(),
				hotel.getName(),
				hotel.getCity()
		);
		
		return hotelDto;
	}
}