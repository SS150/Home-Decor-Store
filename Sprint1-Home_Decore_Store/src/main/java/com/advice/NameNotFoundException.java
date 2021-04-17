package com.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NameNotFoundException  extends Exception{
	
	NameNotFoundException(String message){
		super(message);
	}

}
