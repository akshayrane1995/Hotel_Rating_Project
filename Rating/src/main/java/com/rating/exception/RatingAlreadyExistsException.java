package com.rating.exception;

public class RatingAlreadyExistsException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public RatingAlreadyExistsException(String message) {
		super(message); 
	}
}
