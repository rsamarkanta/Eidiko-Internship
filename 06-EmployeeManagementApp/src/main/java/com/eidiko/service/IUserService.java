package com.eidiko.service;

import java.util.List;
import java.util.Optional;

import com.eidiko.entity.User;

public interface IUserService {
	public String saveUser(User user);

	public Optional<User> findByUsername(String username);

	public List<User> getAllUserDetails();
}
