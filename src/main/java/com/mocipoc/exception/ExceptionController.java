package com.mocipoc.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(value=IOException.class)
	public ResponseEntity<String> fileNotFound(){
		return new ResponseEntity<String>("File not found at location",HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> genericException(){
		return new ResponseEntity<String>("oops..!!!  Something went wrong ",HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
