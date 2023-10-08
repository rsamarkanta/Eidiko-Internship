package com.eidiko.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.Employee;
import com.eidiko.exception.ResourceNotFoundException;
import com.eidiko.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Long createEmployee(Employee employee) {
		empRepo.save(employee);
		return employee.getEmpId();
	}

	@Override
	public List<Employee> getAllEmployee() {
		return empRepo.findAll();
	}

	@Override
	public Employee getById(int empId) {
		return empRepo.findById(empId).orElseThrow(
				() -> new ResourceNotFoundException("Employee information is not available with this Id."));
	}

	@Override
	public Integer deleteById(int empId) {
		empRepo.findById(empId).orElseThrow(
				() -> new ResourceNotFoundException("Employee information is not available with this Id."));
		empRepo.deleteById(empId);
		return empId;
	}

	@Override
	public Long updateEmployeeById(int empId, Employee emp) {
		Employee employee = empRepo.findById(empId).orElseThrow(
				() -> new ResourceNotFoundException("Employee information is not available with this Id."));
		employee.setEmpName(emp.getEmpName());
		employee.setSalary(emp.getSalary());
		// employee.setAddress(emp.getAddress());
		empRepo.save(employee);
		return employee.getEmpId();
	}

	public void saveEmployeesToDatabase(MultipartFile file) {
		System.out.println("Inside excel reder service");
		if (ExcelUploadService.isValidExcelFile(file)) {

			try {
				List<Employee> empList = ExcelUploadService.getCustomersDataFromExcel(file.getInputStream());
				this.empRepo.saveAll(empList);
			} catch (IOException e) {
				throw new IllegalArgumentException("The file is not a valid excel file");
			}
			// } else {
			// throw new InvalidFileFormatException("Only Excel file is permited");
		}
	}

}
