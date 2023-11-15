package com.example.swagger.api.exception;

public class InvalidAgeException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public InvalidAgeException(String message) {
        super(message);
    }
}
