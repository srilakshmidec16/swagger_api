package com.example.swagger.api.exception;

public class EmptyFirstNameException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EmptyFirstNameException(String message) {
        super(message);
    }
}

