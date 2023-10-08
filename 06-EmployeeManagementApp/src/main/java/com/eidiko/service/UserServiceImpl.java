package com.eidiko.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.entity.User;
import com.eidiko.exception.EmployeeNotFoundException;
import com.eidiko.exception.UserAlreadyExistsException;
import com.eidiko.exception.UsernameAlreadyExistsException;
import com.eidiko.repository.EmployeeRepository;
import com.eidiko.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	EmployeeService employeeService;

	@Override
	public String saveUser(User user) {
		Employee emp = employeeRepository.findByEmpName(user.getEmpName().toString())
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with this name"));

		String empName = emp.getEmpName();
		String userName = user.getEmpName();

		Optional<User> existingUsername = findByUsername(user.getUsername().toString());
		System.out.println("Existing username for any employee :: " + existingUsername);
		Optional<User> existingEmpUsername = getUsernameByEmployeename(user.getUsername().toString(),
				emp.getEmpName().toString());

		System.out.println("Existing Employee username :: " + existingEmpUsername);

		if (userName.equals(empName)) {
			if (existingUsername.isEmpty()) {
				try {
					User user2 = new User();
					user2.setEmpName(user.getEmpName());
					user2.setUsername(user.getUsername());
					user2.setRoles(user.getRoles());
					System.out.println(user2);
					user2.setPassword(pwdEncoder.encode(user.getPassword()));
					return "User saved with Username :: " + userRepo.save(user2).getUsername();
				} catch (ConstraintViolationException e) {
					throw new UserAlreadyExistsException("Employee already registered with another username");
				}
			} else
				throw new UsernameAlreadyExistsException("Username is not available try with new username !");
		} else
			throw new EmployeeNotFoundException("User name is not matching with any Employee name");
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optUser = findByUsername(username);

		if (optUser.isEmpty())
			throw new UsernameNotFoundException("User does not exist !");

		// read user from data base
		User user = optUser.get();
		List<SimpleGrantedAuthority> autherities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), autherities);
	}

	public List<User> getAllUserDetails() {
		return userRepo.findAll();
	}

	public Optional<User> getUsernameByEmployeename(String username, String empName) {
		Optional<User> user = userRepo.findByUsernameAndEmpName(username, empName);
		return user;
	}

}
