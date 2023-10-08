package com.eidiko.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.Employee;

@Service
public interface IEmployeeService {

//create,read,update,delete,
	public Long createEmployee(Employee emp);

	public List<Employee> getAllEmployee();

	public Employee getById(int empId);

	public Integer deleteById(int empId);

	public Long updateEmployeeById(int empId, Employee emp);

	public void saveEmployeesToDatabase(MultipartFile file);
}
