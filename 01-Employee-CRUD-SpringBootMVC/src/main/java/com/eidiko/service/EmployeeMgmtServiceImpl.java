package com.eidiko.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eidiko.model.Employee;
import com.eidiko.repository.IEmployeeRepo;

@Service("empService")
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {

	@Autowired
	private IEmployeeRepo empRepo;

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll(Sort.by("job").ascending());
	}

	@Override
	public String insertEmployee(Employee emp) {
		return "Employee inserted with id no :: " + empRepo.save(emp).getId();
	}

	@Override
	public int save(Employee emp) {
		return empRepo.save(emp).getId();
	}

	@Override
	public void update(Employee emp, int id) {
		Employee employee = empRepo.findById(id).orElseThrow();
		employee.setName(emp.getName());
		employee.setSalary(emp.getSalary());
		employee.setDesignation(emp.getDesignation());
		empRepo.save(employee);
	}

	@Override
	public int delete(int id) {
		empRepo.deleteById(id);
		return id;
	}

	@Override
	public Employee getEmpById(int id) {
		return empRepo.findById(id).orElseThrow();
	}

	@Override
	public List<Employee> getEmployees() {
		return empRepo.findAll();
		// (Sort.by("id").ascending());
	}

}
