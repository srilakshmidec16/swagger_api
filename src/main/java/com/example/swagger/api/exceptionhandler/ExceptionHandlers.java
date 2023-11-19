package com.example.swagger.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
public class ExceptionHandlers  {
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException
    		ex){
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", false);
        body.put("error", true);
        body.put("data", null);
        List<ObjectError> errors = new ArrayList<>();
        errors.addAll(ex.getAllErrors());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

     }
	 @ExceptionHandler(ConstraintViolationException.class)
     public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
		 Map<String, Object> body = new LinkedHashMap<>();
	        body.put("success", false);
	        body.put("error", true);
	        body.put("data", null);
	        List<FieldError> errors = ex.getConstraintViolations()
                    .stream()
                    .map(constraintViolation -> {
                        return new FieldError(constraintViolation.getRootBeanClass().getName()
                        		+ " " + constraintViolation.getPropertyPath(), constraintViolation.getMessage(), null);
                    })
                    .collect(Collectors.toList());
	        body.put("errors", errors);

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

     }
}