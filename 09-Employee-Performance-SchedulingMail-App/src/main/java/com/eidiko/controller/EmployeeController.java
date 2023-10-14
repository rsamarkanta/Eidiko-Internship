package com.eidiko.controller;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.Employee;
import com.eidiko.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@PostMapping("/saveEmployee")
	public ResponseEntity<Map<String, Object>> saveEmp(@RequestBody Employee emp) {
		empService.saveEmp(emp);

		Map<String, Object> response = new HashMap<>();
		response.put("Message", "EMployee Data saved successfuly");
		response.put("status", HttpStatus.CREATED);
		response.put("Result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@GetMapping("/getAllTraineeEmployee")
	public ResponseEntity<Map<String, Object>> getAll() {
		List<Employee> employee = empService.getAllTrainee();
		Map<String, Object> response = new HashMap<>();
		if (employee != null) {
			response.put("Message", employee);
			response.put("status", HttpStatus.CREATED);
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			response.put("Message", "EMployee Data saving failed");
			response.put("status", HttpStatus.CREATED);
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		}
	}

	// @Scheduled(cron = "0 * 1-10 * * ?") // this is for every month 1th date to
	// 10th date
	// @Scheduled(cron = "10 * * * * ?")
	@GetMapping("/sendPerformanceMail")
	public ResponseEntity<Map<String, Object>> scheduledMethod() {

		log.debug(" Scheduled Method called.....");

		log.info("Now mail send :: " + LocalTime.now());

		empService.sendEmailWithAttachment();

		log.info("Now mail send :: " + LocalTime.now());
		Map<String, Object> response = new HashMap<>();
		response.put("Message", "Mail Send Successfully");
		response.put("status", HttpStatus.CREATED);
		response.put("Result", "Success");

		log.debug(" Scheduled Method Execution Completed.....");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
