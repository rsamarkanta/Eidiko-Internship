package com.eidiko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.entity.User;
import com.eidiko.exception.IdNotFoundException;
import com.eidiko.exception.UserAlreadyExistsException;
import com.eidiko.exception.UserNotFoundException;
import com.eidiko.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public String addUser(User user) {
		// userRepo.save(user);

		User existingUser = userRepo.findByUsername(user.getUsername()).orElse(null);
		if (existingUser == null) {
			userRepo.save(user);
			return "User added successfully with id no :: " + user.getUserId();
		} else
			throw new UserAlreadyExistsException("User already exists!!");

	}

	@Override
	public List<User> allUser() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(int id) {

		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with this ID !"));
	}

	@Override
	public String deleteUser(int id) {

		User user = userRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id not avaiable !"));

		userRepo.delete(user);
		return "User removed Successfully ";
	}

	@Override
	public String updateUser(User usr, int id) {

		User user = userRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id not available !"));

		user.setUsername(usr.getUsername());
		user.setPassword(usr.getPassword());
		user.setEmail(usr.getPassword());
		user.setMobileNo(usr.getMobileNo());
		userRepo.save(user);
		return "User updated Successfully";
	}
}
