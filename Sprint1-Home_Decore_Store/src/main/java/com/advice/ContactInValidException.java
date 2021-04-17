package com.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import net.bytebuddy.implementation.bind.annotation.Super;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ContactInValidException extends Exception{
	
	public ContactInValidException(String message) {
		
		super(message);
	}

}
