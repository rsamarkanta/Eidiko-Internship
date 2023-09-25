package com.eidiko.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.User;
import com.eidiko.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IUserService userService;

	@GetMapping("/message")
	public String message() {
		return "Successful";
	}

	@PostMapping("/addNewUser")
	public ResponseEntity<String> addNewUser(@Valid @RequestBody User user) {
		String message = userService.addUser(user);
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}

	@GetMapping("/getUserById/{id}")
	public ResponseEntity<User> getUserById(@PathVariable int id) {
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> allUser = userService.allUser();
		return new ResponseEntity<>(allUser, HttpStatus.OK);
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUser(@Valid @RequestBody User user, @PathVariable int id) {
		String message = userService.updateUser(user, id);
		return new ResponseEntity<>(message, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		String message = userService.deleteUser(id);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
}
