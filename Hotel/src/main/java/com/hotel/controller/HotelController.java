package com.hotel.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.HotelDto;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	private HotelService hotelService;

	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}

	@PostMapping("/create")
	public ResponseEntity<HotelDto> createHotel(@RequestBody HotelDto hotelDto) {
		HotelDto saveHotel = hotelService.createHotel(hotelDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveHotel);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id){
		HotelDto hotelDto = hotelService.getHotelById(id);
		return ResponseEntity.ok(hotelDto);
	}
	
	@GetMapping
	public ResponseEntity<List<HotelDto>> getAllHotels(){
		List<HotelDto> hotels = hotelService.getAllHotels();
		return ResponseEntity.ok(hotels);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<HotelDto> updateHotel(@PathVariable Long id  ,@RequestBody HotelDto hotelDto){
		HotelDto updatedhotel = hotelService.updateHotel(id,hotelDto);
		return ResponseEntity.ok(updatedhotel);
	}
	
	@DeleteMapping("/id/{id}/remove")
	public ResponseEntity<String> deleteHotel(@PathVariable Long id){
			hotelService.deleteHotel(id);
			return ResponseEntity.ok("Hotel is deleted successfully");
	}
}
