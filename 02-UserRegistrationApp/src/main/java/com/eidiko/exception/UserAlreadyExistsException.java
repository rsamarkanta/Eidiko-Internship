package com.eidiko.exception;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistsException() {
	}

	public UserAlreadyExistsException(String msg) {
		super(msg);
	}
}
