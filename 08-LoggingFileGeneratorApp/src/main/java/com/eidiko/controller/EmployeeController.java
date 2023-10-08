package com.eidiko.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.eidiko.entity.Employee;
import com.eidiko.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/welcome")
	public ResponseEntity<Map<String, Object>> welcome() {

		log.debug("welcome method started in Controller");
		String message = "Welcome to Employee Management Application";

		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", message);
		response.put("status", HttpStatus.OK.value());
		log.info("'welcome' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/showAllEmployee")
	public ResponseEntity<Map<String, Object>> showAllEmployee() {

		log.debug("'showAllEmployee' method started in Controller");
		List<Employee> message = employeeService.getAllEmp();

		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("Total Employee", message.size());
		response.put("message", message);
		response.put("status", HttpStatus.OK.value());

		log.info("'showAllEmployee' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee emp) {
		log.debug("'addEmployee' method started in Controller");
		String message = employeeService.saveEmployee(emp);
		Map<String, Object> response = new HashMap<>();
		response.put("Result", " Success");
		response.put("message", message);
		response.put("status", HttpStatus.CREATED.value());
		log.info("'addEmployee' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/showEmployeeById/{empId}")
	public ResponseEntity<Map<String, Object>> showEmployeeById(@PathVariable Long empId) {
		log.debug("'showEmployeeById' method started in Controller");
		Employee message = employeeService.getEmployeeById(empId);
		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", message);
		response.put("status", HttpStatus.OK.value());

		log.info("'showEmployeeById' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/updateEmployeeById/{empId}")
	public ResponseEntity<Map<String, Object>> updateEmployeeById(@RequestBody Employee emp, @PathVariable Long empId) {
		log.debug("'updateEmployeeById' method started in Controller");
		String message = employeeService.updateEmployee(emp, empId);

		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", message);
		response.put("status", HttpStatus.OK.value());
		log.info("'updateEmployeeById' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteEmployeeById/{empId}")
	public ResponseEntity<Map<String, Object>> deleteEmployeeById(@PathVariable Long empId) {
		log.debug("'deleteEmployeeById' method started in Controller");
		String message = employeeService.deleteEmployeeById(empId);
		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", message);
		response.put("status", HttpStatus.OK.value());
		log.info("'deleteEmployeeById' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllDeletedEmployee")
	public ResponseEntity<Map<String, Object>> getAllDeletedEmployee() {
		log.debug("'getAllDeletedEmployee' method started in Controller");
		List<Employee> result = employeeService.getAllDeletedEmployee();
		Map<String, Object> response = new HashMap<>();
		response.put("Message ", "Data fetched successfully");
		response.put("Result", result);
		response.put("totalEmployees", result.size());
		response.put("status", HttpStatus.OK.value());
		log.info("'getAllDeletedEmployee' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllActiveEmployee")
	public ResponseEntity<Map<String, Object>> getAllActiveEmployee() {
		log.debug("'getAllActiveEmployee' method started in Controller");
		List<Employee> message = employeeService.getAllActiveEmployee();
		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Data fetched successfully");
		response.put("message", message);
		response.put("totalEmployees", message.size());
		response.put("status", HttpStatus.OK.value());
		log.info("'getAllActiveEmployee' method return Response to the User");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
