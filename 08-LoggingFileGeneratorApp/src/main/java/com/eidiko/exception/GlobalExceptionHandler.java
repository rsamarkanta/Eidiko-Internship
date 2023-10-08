package com.eidiko.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eidiko.entity.ApiError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		log.error("HttpRequestMethodNotSupportedException Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request Method not Supported");
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		log.error("return HttpRequestMethodNotSupportedException Message, Status :: " + status);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("HttpMediaTypeNotSupportedException Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Media not Supported");
		details.add(ex.getMessage());
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		log.error("return HttpMediaTypeNotSupportedException Message, Status :: " + status);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error("MissingPathVariableException Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Path Variable is missing !");
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		log.error("return MissingPathVariableException Message, Status :: " + status);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("MissingServletRequestParameterException Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request param is missing !");
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		log.error("return MissingServletRequestParameterException Message, Status :: " + status);
		return ResponseEntity.status(status).body(errors);

	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.error("TypeMismatchException Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Mismatch of type !");
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		log.error("return TypeMismatchException Message, Status :: " + status);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("HttpMessageNotReadableException Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request Body is not Readable !");
		ApiError errors = new ApiError(message, details, status, LocalDateTime.now());
		log.error("return HttpMessageNotReadableException Message, Status :: " + status);
		return ResponseEntity.status(status).body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception ex) {
		log.error("Other Exception Occured");
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Other Exception !");
		details.add(ex.getMessage());
		ApiError errors = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
		log.error("return Other Exception Message, Status :: " + HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

}
