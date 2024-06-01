package com.personal.chronikale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequestValidationExecption extends RuntimeException{

	public RequestValidationExecption(String message) {
		super(message);
	}
	

}
