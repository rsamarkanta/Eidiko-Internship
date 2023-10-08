package com.eidiko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.exception.EmployeeNotFoundException;
import com.eidiko.exception.IdNotFoundException;
import com.eidiko.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(Employee emp) {

		log.info("'saveEmployee' method called by User");
		Employee employee = employeeRepository.save(emp);

		log.info("Get Employee Information from controller and save to DB");
		log.info("Return newly created Employee ID to the Controller");
		return " Employee saved with ID :: " + employee.getEmpId();
	}

	@Override
	public Employee getEmployeeById(Long empId) {
		log.info("'getEmployeeById' method called by User");
		log.info("Find Employee Information by Employee ID given by User");
		Employee employee = employeeRepository.findById(empId)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with this ID !"));

		log.info("Return Employee Information to the Controller");
		return employee;
	}

	@Override
	public List<Employee> getAllEmp() {
		log.info("'getAllEmp' method called by User");
		log.info("Find All Employee Information");

		List<Employee> empList = employeeRepository.findAll();
		log.info("Return All Employee Information to the Controller");
		return empList;
	}

	@Override
	public String updateEmployee(Employee emp, Long empId) {
		log.info("'updateEmployee' method called by User");
		log.info("Find Existed Employee Information by using Employee ID given by User");
		Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("Empl"));
		employee.setEmpName(emp.getEmpName());
		employee.setDoJ(emp.getDoJ());
		employee.setAbout(emp.getAbout());
		employee.setContactNo(emp.getContactNo());
		employee.setStatus(emp.getStatus());
		employee.setDesignation(emp.getDesignation());
		log.info("Update exising Employee Information by New Employee Information");
		employeeRepository.save(employee);

		log.info("Return Update Confirmation Message to the Controller");
		return "Employee updated successfully !";
	}

	@Override
	public String deleteEmployeeById(Long empId) {
		log.info("'deleteEmployeeById' method called by User");
		log.info("Find Existed Employee Information by using Employee ID given by User");
		Employee employee = employeeRepository.findById(empId).orElseThrow(() -> new IdNotFoundException());
		Employee emp = new Employee();
		String status = employee.getStatus();

		if (status.equalsIgnoreCase("active")) {
			emp.setEmpId(employee.getEmpId());
			emp.setEmpName(employee.getEmpName());
			emp.setContactNo(employee.getContactNo());
			emp.setDesignation(employee.getDesignation());
			emp.setDeleted(true);
			emp.setStatus("Inactive");
			log.info("Delete exising Employee Information");
			employeeRepository.save(emp);
		}
		log.info("Return Update Confirmation Message to the Controller");
		return "Employee removed succesfully !";
	}

	// For All Deleted Employee Data
	@Override
	public List<Employee> getAllDeletedEmployee() {
		log.info("'getAllDeletedEmployee' method called by User");
		log.info("Find All Deleted Employee Information from Database");
		List<Employee> empList = employeeRepository.getInactiveEmployeeOnly();
		log.info("Return List of All deleted Employee Information to the Controller");
		return empList;
	}

	@Override
	public List<Employee> getAllActiveEmployee() {
		log.info("'getAllActiveEmployee' method called by User");
		log.info("Find All Active Employee Information from Database");
		List<Employee> empList = employeeRepository.getActiveEmployee();
		log.info("Return List of All deleted Employee Information to the Controller");
		return empList;
	}

}
