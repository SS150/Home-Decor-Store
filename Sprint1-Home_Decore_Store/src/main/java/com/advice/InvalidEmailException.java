package com.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class InvalidEmailException extends Exception {
	
	public InvalidEmailException(String message) {
		super(message);
	}

}
