package com.eidiko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTime;
import com.eidiko.exception.EmployeeInactiveException;
import com.eidiko.exception.EmployeeNotFoundException;
import com.eidiko.exception.IdNotFoundException;
import com.eidiko.repository.EmployeeRepository;
import com.eidiko.repository.ShiftTimeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ShiftTimeRepo shiftTimeRepository;

	@Override
	public String saveEmployee(Employee emp) {

		employeeRepository.save(emp);

		return "Processed successfully";
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with this ID !"));
		String status = employee.getStatus();
		if (status.equalsIgnoreCase("active")) {
			return employee;
		} else
			throw new EmployeeInactiveException("Employee is not active now.");
	}

	@Override
	public Employee getEmployeeByName(String name) {
		Employee employee = employeeRepository.findByEmpName(name)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with this name !"));

		String status = employee.getStatus();
		if (status.equalsIgnoreCase("active")) {
			return employee;
		} else
			throw new EmployeeInactiveException("Employee is not in active state.");
	}

	@Override
	public List<Employee> getAllEmp() {

		return employeeRepository.findAll();
	}

	@Override
	public String updateEmployee(Employee emp, Long empId) {

		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not Found"));
		String status = employee.getStatus();
		if (status.equalsIgnoreCase("active")) {
			employee.setEmpName(emp.getEmpName());
			employee.setDoJ(emp.getDoJ());
			employee.setAbout(emp.getAbout());
			employee.setContactNo(emp.getContactNo());
			employee.setDesignation(emp.getDesignation());
			employeeRepository.save(employee);
			return "Employee updated successfully !";
		} else
			throw new EmployeeInactiveException("Employee is not active now.");
	}

	@Override
	public String deleteEmployeeById(Long empId) {
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new IdNotFoundException("Employee Id is not available."));
		Employee emp = new Employee();
		String status = employee.getStatus();
		if (status.equalsIgnoreCase("active")) {
			emp.setEmpId(employee.getEmpId());
			emp.setEmpName(employee.getEmpName());
			emp.setContactNo(employee.getContactNo());
			emp.setAbout(employee.getAbout());
			emp.setDoJ(employee.getDoJ());
			emp.setDesignation(employee.getDesignation());
			emp.setShift(employee.getShift());
			emp.setDeleted(true);
			emp.setStatus("InActive");
			employeeRepository.save(emp);
			return "Employee removed succesfully !";
		} else
			throw new EmployeeInactiveException("Employee is not active now.");
	}

	@Override
	public List<Employee> getAllDeletedEmployee() {
		List<Employee> empList = employeeRepository.getInactiveEmployeeOnly();
		return empList;
	}

	// For All Deleted Employee Data
	@Override
	public List<Employee> getAllActiveEmployee() {
		List<Employee> empList = employeeRepository.getActiveEmployee();
		return empList;
	}

	@Override
	public ShiftTime getLatestShiftByEmpId(Long empId) {
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new IdNotFoundException("Employee Id is not available."));
		String status = employee.getStatus();
		if (status.equalsIgnoreCase("active")) {
			return shiftTimeRepository.findLatestShiftTimeByEmpId(empId);
		} else
			throw new EmployeeInactiveException("Employee is not active now.");
	}

}
