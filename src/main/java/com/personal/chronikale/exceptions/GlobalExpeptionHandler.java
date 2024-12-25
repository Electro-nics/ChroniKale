package com.personal.chronikale.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.personal.chronikale.responsePayload.ApplicationResponsePayload;

@RestControllerAdvice
public class GlobalExpeptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApplicationResponsePayload> ResourceNotFoundHandler(ResourceNotFound ex){
		String message= ex.getMessage();
		ApplicationResponsePayload responsePayload=
				new ApplicationResponsePayload(message, false);
		return new ResponseEntity<>(responsePayload,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(DuplicateResourceException.class)
	public ResponseEntity<ApplicationResponsePayload> DuplicateResourceExceptionHandler(DuplicateResourceException ex){
		String message = ex.getMessage();
		ApplicationResponsePayload responsePayload= new ApplicationResponsePayload(message, false);
		return new ResponseEntity<>(responsePayload,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(RequestValidationExecption.class)
	public ResponseEntity<ApplicationResponsePayload> RequestValidationHandler(RequestValidationExecption ex){
		String message= ex.getMessage();
		ApplicationResponsePayload responsePayload = new ApplicationResponsePayload(message, false);
		return new ResponseEntity<>(responsePayload, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		
		Map<String, String> response= new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message= error.getDefaultMessage();
			response.put(fieldName, message);
		});
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserLoginExecption.class)
	public ResponseEntity<ApplicationResponsePayload> userLoginExceptionHandler(UserLoginExecption ex){
		String message= ex.getMessage();
		ApplicationResponsePayload responsePayload=
				new ApplicationResponsePayload(message, false);
		return new ResponseEntity<>(responsePayload,HttpStatus.BAD_REQUEST);
		
	}
}
