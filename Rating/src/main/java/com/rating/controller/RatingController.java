package com.rating.controller;

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

import com.rating.dto.RatingDto;
import com.rating.service.RatingService;

@RestController
@RequestMapping("/rating")
public class RatingController {

	private RatingService ratingService;
	
	public RatingController(RatingService ratingService) {
		this.ratingService = ratingService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<RatingDto> createRating(@RequestBody RatingDto ratingDto){
		RatingDto saveRating = ratingService.createRating(ratingDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saveRating);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<RatingDto> getRatingById(@PathVariable Long id){
		RatingDto ratingDto = ratingService.getRatingById(id);
		return ResponseEntity.ok(ratingDto);
	}
	
	@GetMapping
	public ResponseEntity<List<RatingDto>> getAllHRatings(){
		List<RatingDto> ratings = ratingService.getAllRatings();
		return ResponseEntity.ok(ratings);
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<RatingDto> updateRating(@PathVariable Long id  ,@RequestBody RatingDto ratingDto){
		RatingDto updatedRating = ratingService.updateRating(id,ratingDto);
		return ResponseEntity.ok(updatedRating);
	}
	
	@DeleteMapping("/id/{id}/remove")
	public ResponseEntity<String> deleteRating(@PathVariable Long id){
			ratingService.deleteRating(id);
			return ResponseEntity.ok("Rating is deleted successfully");
	}
}
