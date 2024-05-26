package com.personal.chronikale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
@SuppressWarnings("serial")
public class ResourceNotFound extends RuntimeException{

	public ResourceNotFound(String message) {
		super(message);
	}
	

}
