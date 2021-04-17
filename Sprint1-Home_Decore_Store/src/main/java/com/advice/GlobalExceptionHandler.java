package com.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.advice.ErrorDetails;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(IDNotFoundException.class)
	public ResponseEntity<?> idNotFoundException(IDNotFoundException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<?> invalidCredentialException(InvalidCredentialException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidEmailException.class)
	public ResponseEntity<?> invalidEmailException(InvalidEmailException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<?> nameNotFoundException(NameNotFoundException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(LogInNotFoundException.class)
	public ResponseEntity<?> loginNotFoundException(LogInNotFoundException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) 
	 { 
		 ErrorDetails	  errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),request.getDescription(false)); 
	    return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR); 
	 }
	 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) 
	 { 
		 ErrorDetails	  errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getBindingResult().getFieldError().getDefaultMessage(),request.getDescription(false)); 
	    return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST); 
	 }
	
	
	@ExceptionHandler(ContactInValidException.class)
	public ResponseEntity<?> contactInValidException(ContactInValidException ex, WebRequest request){
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
}
