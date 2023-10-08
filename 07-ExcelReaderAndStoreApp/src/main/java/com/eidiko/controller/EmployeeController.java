package com.eidiko.controller;

import java.util.List;

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
import com.eidiko.service.IEmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService empService;

	@GetMapping("/welcome")
	public String welcome() {
		return "WELCOME";
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> showAllEmployee() {
		List<Employee> listEmp = empService.getAllEmployee();
		return new ResponseEntity<List<Employee>>(listEmp, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> showEmployeeById(@PathVariable int id) {
		Employee emp = empService.getById(id);
		return new ResponseEntity<Employee>(emp, HttpStatus.ACCEPTED);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody Employee emp) {
		Long id = empService.createEmployee(emp);
		return new ResponseEntity<String>("Employee saved with id no :: " + id, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee emp, @PathVariable int id) {
		Long empId = empService.updateEmployeeById(id, emp);
		return new ResponseEntity<String>("Employee details updated for id no :: " + empId, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) {
		Integer empId = empService.deleteById(id);
		return new ResponseEntity<String>("Employee deleted with id no :: " + empId, HttpStatus.ACCEPTED);
	}
}
