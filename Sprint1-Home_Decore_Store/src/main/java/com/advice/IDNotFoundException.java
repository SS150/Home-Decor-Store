package com.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class IDNotFoundException extends Exception {

	public IDNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
