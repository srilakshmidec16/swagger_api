package com.example.swagger.api.exception;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler  {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException
    		ex){
		Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", false);
        body.put("error", ex.getMessage());
        body.put("data", null);
        List<ObjectError> errors = new ArrayList<>();
        errors.addAll(ex.getAllErrors());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

     }
	
    @ExceptionHandler(EmptyFirstNameException.class)
    public ResponseEntity<Object> handleEmptyFirstNameException(EmptyFirstNameException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", false);
        body.put("error", true);
        body.put("data", null);
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<Object> handleInvalidAgeException(InvalidAgeException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", false);
        body.put("error", true);
        body.put("data", null);
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidMobileNumberException.class)
    public ResponseEntity<Object> handleInvalidMobileNumberException(InvalidMobileNumberException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("success", false);
        body.put("error", true);
        body.put("data", null);
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
   }
}