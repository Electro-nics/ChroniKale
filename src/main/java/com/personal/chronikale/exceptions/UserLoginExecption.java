package com.personal.chronikale.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@SuppressWarnings("serial")
public class UserLoginExecption extends RuntimeException{

	public UserLoginExecption(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	

}
