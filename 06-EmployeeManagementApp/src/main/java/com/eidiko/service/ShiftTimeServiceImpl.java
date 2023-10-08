package com.eidiko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTime;
import com.eidiko.exception.EmployeeInactiveException;
import com.eidiko.exception.IdNotFoundException;
import com.eidiko.repository.EmployeeRepository;
import com.eidiko.repository.ShiftTimeRepo;

@Service
public class ShiftTimeServiceImpl implements ShiftTimeService {

	@Autowired
	private ShiftTimeRepo shiftRepo;
	@Autowired
	private EmployeeRepository empRepo;

	// it is for saving shift values based on empId
	@Override
	public String saveShiftByEmployeeId(ShiftTime shift, Long empId) {
		Employee employee = empRepo.findById(empId).orElseThrow(() -> new IdNotFoundException("Employee not found"));

		String status = employee.getStatus();
		if (status.equalsIgnoreCase("active")) {
			shift.setEmployee(employee);
			shift.setModifyBy(empId);
			shiftRepo.save(shift);
			return "Shift added for the Employee with Employee ID :: " + employee.getEmpId();
		} else
			throw new EmployeeInactiveException("Employee is not in active state.");
	}

	@Override
	public List<ShiftTime> getAllShift() {
		System.out.println("ShiftTimeServiceImpl.getAllShift()");
		return shiftRepo.findAll();
	}

	@Override
	public List<ShiftTime> getAllShiftsByEmployeeId(Long empId) {
		Employee employee = empRepo.findById(empId).orElseThrow(() -> new IdNotFoundException("Employee Id not found"));
		String status = employee.getStatus();
		if (status.equalsIgnoreCase("Active")) {
			return shiftRepo.findByEmployeeEmpId(empId);
		} else
			throw new EmployeeInactiveException("Employee is not in active state.");
	}

	// for getting All "shift values" based on 'empId'
	@Override
	public ShiftTime getShiftByShiftId(Long shiftId) {
		return shiftRepo.findById(shiftId).orElseThrow(() -> new IdNotFoundException("Shift ID not found"));
	}

}
