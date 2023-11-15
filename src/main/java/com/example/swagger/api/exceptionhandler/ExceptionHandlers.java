package com.example.swagger.api.exceptionhandler;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.swagger.api.exception.EmpytDataFoundException;
import com.example.swagger.api.exception.NoDataUpdatedException;
import com.example.swagger.api.exception.NoSufficientDataException;
import com.example.swagger.api.exception.ResponseFormat;
import com.example.swagger.api.exception.UserNotFoundException;

@ControllerAdvice
@ResponseStatus
public class ExceptionHandlers extends ResponseEntityExceptionHandler {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
		return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmpytDataFoundException.class)
	public ResponseEntity<String> handleNoUsers(EmpytDataFoundException emptyUserException) {
		return new ResponseEntity<String>("No Users are there in the DataBase", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoSufficientDataException.class)
	public ResponseFormat handleNoSufficientDataException(NoSufficientDataException noSufficientDataException,WebRequest request) {
		ResponseFormat responseFormat=new  ResponseFormat();
		responseFormat.setError(true);
		responseFormat.setSuccess(false);
		responseFormat.setData(noSufficientDataException.getPayLoad());
		String errors[]= {noSufficientDataException.getMessage()};
		responseFormat.setErrors(errors);
//		ErrorMessage errorMessage=new ErrorMessage(HttpStatus.BAD_REQUEST,noSufficientDataException.getMessage(), "400");
//		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
		return responseFormat;
	}

	@ExceptionHandler(NoDataUpdatedException.class)
	public ResponseEntity<String> handleNodataUpdatedException(NoDataUpdatedException noDataUpdatedException) {

		return new ResponseEntity<String>("No Data is updated to save in DataBase", HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<String> handleNumberFormatException(NumberFormatException numberFormatException) {
		return new ResponseEntity<String>("String id is not allowed here", HttpStatus.BAD_REQUEST);
	}
}