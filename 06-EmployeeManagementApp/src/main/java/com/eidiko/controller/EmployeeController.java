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
import com.eidiko.entity.ShiftTime;
import com.eidiko.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/home")
	public ResponseEntity<Map<String, Object>> home() {
		String message = "Welcome to Employee Management Application";
		String message2 = "This is Home Page & accesible to All";
		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", message);
		response.put("message", message2);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/welcome")
	public ResponseEntity<Map<String, Object>> welcome() {
		String message = "Welcome to Employee Management Application";

		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", message);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/showAllEmployee")
	public ResponseEntity<Map<String, Object>> showAllEmployee() {

		List<Employee> result = employeeService.getAllEmp();

		Map<String, Object> response = new HashMap<>();
		response.put("message", "Data Fetched Successfully");
		response.put("total Employee", result.size());
		response.put("result", result);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/addEmployee")
	public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody Employee emp) {

		String result = employeeService.saveEmployee(emp);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Success");
		response.put("result", result);
		response.put("status", HttpStatus.CREATED.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/showEmployeeById/{empId}")
	public ResponseEntity<Map<String, Object>> showEmployeeById(@PathVariable Long empId) {

		Employee result = employeeService.getEmployeeById(empId);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Data Fetched Successfully");
		response.put("result", result);
		response.put("status", HttpStatus.OK.value());

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PutMapping("/updateEmployeeById/{empId}")
	public ResponseEntity<Map<String, Object>> updateEmployeeById(@RequestBody Employee emp, @PathVariable Long empId) {

		String result = employeeService.updateEmployee(emp, empId);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "success");
		response.put("Result", result);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deleteEmployeeById/{empId}")
	public ResponseEntity<Map<String, Object>> deleteEmployeeById(@PathVariable Long empId) {

		String result = employeeService.deleteEmployeeById(empId);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "success");
		response.put("Result", result);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getLatestShift/{empId}")
	public ResponseEntity<Map<String, Object>> getLatestShift(@PathVariable Long empId) {
		ShiftTime latestShift = employeeService.getLatestShiftByEmpId(empId);

		if (latestShift != null) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Latest Shift Fetched Successfully");
			response.put("result", latestShift);
			response.put("status", HttpStatus.OK.value());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			return ResponseEntity.ok().build();
		}
	}

	@GetMapping("/getAllDeletedEmployee")
	public ResponseEntity<Map<String, Object>> getAllDeletedEmployee() {
		List<Employee> result = employeeService.getAllDeletedEmployee();
		Map<String, Object> response = new HashMap<>();
		response.put("Message ", "Data fetched successfully");
		response.put("Result", result);
		response.put("totalEmployees", result.size());
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/getAllActiveEmployee")
	public ResponseEntity<Map<String, Object>> getAllActiveEmployee() {
		List<Employee> result = employeeService.getAllActiveEmployee();
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Data fetched successfully");
		response.put("result", result);
		response.put("total Employees", result.size());
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
