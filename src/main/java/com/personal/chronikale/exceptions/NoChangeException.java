package com.personal.chronikale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.NOT_MODIFIED)
public class NoChangeException extends RuntimeException {

	public NoChangeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
