package com.eidiko.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdNotFoundException() {
		super();
		log.error("ID Not Found Exception Occurred");
	}

	public IdNotFoundException(String message) {

		super(message);
		log.error("ID Not Found Exception Occurred");
	}

}
