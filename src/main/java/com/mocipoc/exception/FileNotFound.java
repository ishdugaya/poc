package com.mocipoc.exception;

public class FileNotFound extends RuntimeException {

	private String message;

	public FileNotFound(String message) {
		super(message);
		this.message = message;
	}
	
	
}
