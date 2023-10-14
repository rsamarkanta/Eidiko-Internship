package com.eidiko.service;


import java.util.List;

import com.eidiko.entity.Employee;

public interface EmployeeService {

	public void sendEmailWithAttachment();
	public List<Employee> getAllTrainee();
	public Employee saveEmp(Employee emp);
	
}
