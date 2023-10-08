package com.eidiko.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.eidiko.entity.ApiError;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMap.put(fieldName, errorMessage);
		});
		return errorMap;
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(EmployeeNotFoundException ex) {
		String message = ex.getMessage();

		List<String> details = new ArrayList<>();
		details.add("Employee not Found !");
		ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Id not available !");
		ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}

	@ExceptionHandler(EmployeeAlreadyExistsException.class)
	public ResponseEntity<Object> handleUserAlreadyExistsException(EmployeeAlreadyExistsException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("User name already exists. Try with new username !");
		ApiError errors = new ApiError(message, details, HttpStatus.CONFLICT, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationExceptionException(ConstraintViolationException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("User name already exists for this employee !");
		ApiError errors = new ApiError(message, details, HttpStatus.CONFLICT, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
	}

	@ExceptionHandler(value = { ExpiredJwtException.class })
	public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String requestUri = ((ServletWebRequest) request).getRequest().getRequestURI().toString();

		// String message = new ExceptionMessage(ex.getMessage(), requestUri);

		// return new ResponseEntity<>(exceptionMessage, new HttpHeaders(),
		// HttpStatus.FORBIDDEN);

		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add(requestUri);
		details.add("Token Expired ! Try again !");
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		return ResponseEntity.status(status).body(errors);
	}

}
