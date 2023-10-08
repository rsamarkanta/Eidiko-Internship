package com.eidiko.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.entity.ShiftTime;

@Service
public interface EmployeeService {

	public String saveEmployee(Employee empl);

	public List<Employee> getAllEmp();

	public Employee getEmployeeByName(String empName);

	public Employee getEmployeeById(Long empId);

	public String updateEmployee(Employee emp, Long empId);

	public String deleteEmployeeById(Long empId);

	public ShiftTime getLatestShiftByEmpId(Long empId);

	public List<Employee> getAllActiveEmployee();

	public List<Employee> getAllDeletedEmployee();

}
