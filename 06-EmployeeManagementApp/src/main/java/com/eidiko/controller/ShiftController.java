package com.eidiko.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.entity.ShiftTime;
import com.eidiko.service.ShiftTimeService;

@RestController
@RequestMapping("/shift")
public class ShiftController {
	@Autowired
	private ShiftTimeService shiftTimeService;

	// Create shift based on empId
	@PostMapping("saveShift/{empId}")
	public ResponseEntity<Map<String, Object>> saveShift(@RequestBody ShiftTime shift, @PathVariable Long empId) {

		String result = shiftTimeService.saveShiftByEmployeeId(shift, empId);

		Map<String, Object> response = new HashMap<>();
		response.put("Message ", "Shift Added for the Employee successfully");
		response.put("Result", result);
		response.put("status", HttpStatus.CREATED.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// Get shift details based on shiftId
	@GetMapping("/getShiftByShiftId/{shiftId}")
	public ResponseEntity<Map<String, Object>> getShiftByShiftId(@PathVariable Long shiftId) {
		ShiftTime result = shiftTimeService.getShiftByShiftId(shiftId);

		Map<String, Object> response = new HashMap<>();
		response.put("Message ", "Shift details fetched successfully");
		response.put("Result", result);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// for All shift details based on empid
	@GetMapping("/getShiftByEmployeeId/{empId}")
	public ResponseEntity<Map<String, Object>> getShifts(@PathVariable Long empId) {
		List<ShiftTime> result = shiftTimeService.getAllShiftsByEmployeeId(empId);

		Map<String, Object> response = new HashMap<>();
		response.put("Message ", "All Shifts of Employee fetched successfully");
		response.put("Result", result);
		response.put("Total Shift", result.size());
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	// for All shift details
	@GetMapping("/getAllShifts")
	public ResponseEntity<Map<String, Object>> getAllShifts() {
		System.out.println("ShiftController.getAllShifts()");

		List<ShiftTime> result = shiftTimeService.getAllShift();

		Map<String, Object> response = new HashMap<>();
		response.put("Message ", "All Shifts fetched successfully");
		response.put("Result", result);
		response.put("Result Size", result.size());
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
