package com.eidiko.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super();
		log.error("Employee Not Found Exception Occurred");
	}

	public EmployeeNotFoundException(String message) {
		super(message);
		log.error("Employee Not Found Exception Occurred");
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

}
