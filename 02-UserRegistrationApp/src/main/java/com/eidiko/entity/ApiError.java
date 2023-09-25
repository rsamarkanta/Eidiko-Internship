package com.eidiko.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	public ApiError(String message2, Map<String, String> errorMap, HttpStatus badRequest, LocalDateTime now) {
		// TODO Auto-generated constructor stub
	}

	String message;
	List<String> details;
	HttpStatus status;
	LocalDateTime timeStamp;

}
