package com.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class InvalidCredentialException extends Exception {
	public InvalidCredentialException(String message) {
		super(message);
		
	}

}
