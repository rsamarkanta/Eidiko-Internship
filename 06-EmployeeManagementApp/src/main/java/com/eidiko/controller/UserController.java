package com.eidiko.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.User;
import com.eidiko.entity.UserRequest;
import com.eidiko.entity.UserResponse;
import com.eidiko.service.IUserService;
import com.eidiko.utility.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private JwtUtil util;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping
	private String hello() {
		return "Hello World !";
	}

	// 1. save user data in database
	@PostMapping("/save")
	private ResponseEntity<Map<String, Object>> saveUser(@RequestBody User user) {
		System.out.println("Controller user" + user.toString());
		String result = userService.saveUser(user);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", result);
		response.put("status", HttpStatus.OK);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	// 2.validate user and generate token(login)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserRequest request) {

		// validate un/pwd with database
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		String token = util.generateToken(request.getUsername());
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		// String token = util.generateToken(request.getUsername());
		UserResponse result = (new UserResponse(token, "success ! Generatd by " + request.getUsername()));
		Map<String, Object> response = new HashMap<>();
		response.put("Result", result);
		response.put("message", "success ! Generatd by " + request.getUsername());
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	// 3.After login only
	@GetMapping("/welcome")
	public ResponseEntity<Map<String, Object>> accessData(Principal p) {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "Welcome to the Employee Portal " + p.getName());
		response.put("status", HttpStatus.OK);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/showAllUsers")
	public ResponseEntity<Map<String, Object>> showAllUserInformation() {

		List<User> result = userService.getAllUserDetails();

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", result);
		response.put("No. of User registered", result.size());
		response.put("status", HttpStatus.OK.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@GetMapping("/accessDenied")
	public ResponseEntity<Map<String, Object>> accessDenied() {

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Processed successfully");
		response.put("Result", "Sorry !! You have NO permission for this Page !!");
		response.put("status", HttpStatus.UNAUTHORIZED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.FORBIDDEN);

	}

}
