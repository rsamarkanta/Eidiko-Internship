package com.eidiko.service;

import java.util.List;

import com.eidiko.model.Employee;

public interface IEmployeeMgmtService {

	public List<Employee> getAllEmployees();

	public String insertEmployee(Employee emp);

	public int save(Employee emp);

	public void update(Employee emp,int id);

	public int delete(int id);

	public Employee getEmpById(int id);

	public List<Employee> getEmployees();
}
