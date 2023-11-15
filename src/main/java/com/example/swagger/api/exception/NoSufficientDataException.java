package com.example.swagger.api.exception;

public class NoSufficientDataException extends RuntimeException {
	Object payLoad;
	public NoSufficientDataException(String message,Object Payload) {
		super(message);
		this.payLoad=payLoad;
	}
	public Object getPayLoad() {
		return payLoad;
	}
	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}
	
}
