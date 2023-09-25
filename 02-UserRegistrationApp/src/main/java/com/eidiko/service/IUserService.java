package com.eidiko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eidiko.entity.User;
import com.eidiko.exception.IdNotFoundException;
import com.eidiko.exception.UserAlreadyExistsException;

@Service
public interface IUserService {

	public String addUser(User user) throws UserAlreadyExistsException;

	public List<User> allUser();

	public User getUserById(int id) throws IdNotFoundException;

	public String updateUser(User user, int id) throws IdNotFoundException;

	public String deleteUser(int id) throws IdNotFoundException;

}
