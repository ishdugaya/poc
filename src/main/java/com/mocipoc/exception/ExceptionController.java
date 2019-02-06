package com.mocipoc.exception;

import java.nio.file.AccessDeniedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value=FileNotFound.class)
	public ResponseEntity<String> fileNotFound(){
		return new ResponseEntity<String>("File not found at location.",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> genericException(){
		return new ResponseEntity<String>("oops..!!!  Something went wrong.",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(value=AccessDeniedException.class)
	public ResponseEntity<String> accessDeniedException(){
		return new ResponseEntity<String>("oops..!!!  You don't have an access.",HttpStatus.LOCKED);
	}
	@ExceptionHandler(value=MissingServletRequestParameterException.class)
	public ResponseEntity<String> missingParamException(){
		return new ResponseEntity<String>("oops..!!!  Request Parameter is not correct.",HttpStatus.BAD_REQUEST);
	}
	
}
