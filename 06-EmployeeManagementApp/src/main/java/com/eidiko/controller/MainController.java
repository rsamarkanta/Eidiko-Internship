package com.eidiko.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {
	@GetMapping("/admin")
	public ResponseEntity<Map<String, Object>> adminHomePage() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "This is ADMIN HOME Page !!");
		response.put("status", HttpStatus.ACCEPTED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping("/manager")
	public ResponseEntity<Map<String, Object>> managerHomePage() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "This is MANAGER HOME Page !!");
		response.put("status", HttpStatus.ACCEPTED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping("/hr")
	public ResponseEntity<Map<String, Object>> hrHomePage() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "This is HR HOME Page !!");
		response.put("status", HttpStatus.ACCEPTED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping("/employee")
	public ResponseEntity<Map<String, Object>> employeeHomePage() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "This is EMPLOYEE HOME Page !!");
		response.put("status", HttpStatus.ACCEPTED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@GetMapping("/visitor")
	public ResponseEntity<Map<String, Object>> visitorHomePage() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "This is VISITOR HOME Page !!");
		response.put("status", HttpStatus.ACCEPTED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping("/common")
	public ResponseEntity<Map<String, Object>> commonPage() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "This is Common Page !!");
		response.put("status", HttpStatus.ACCEPTED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
