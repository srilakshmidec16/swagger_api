package com.example.swagger.api.exception;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

public class ResponseFormat {
    private boolean success;
    private boolean error;
    private Object data;
    private String[] errors;
 
    public ResponseFormat(boolean success, boolean error, Object data) {
        this.success = success;
        this.error = error;
        this.data = data;
        this.errors = new String[0]; // Default empty array for errors
    }
 
    
public ResponseFormat() {
		super();
	}


public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}


	@Override
	public String toString() {
		return "ResponseFormat [success=" + success + ", error=" + error + ", data=" + data + ", errors="
				+ Arrays.toString(errors) + "]";
	}

}