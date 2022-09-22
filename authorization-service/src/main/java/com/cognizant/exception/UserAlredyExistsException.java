package com.cognizant.exception;

public class UserAlredyExistsException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserAlredyExistsException(String message) {
		super(message);
	}
}
