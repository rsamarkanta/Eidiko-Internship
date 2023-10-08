package com.eidiko.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eidiko.entity.Employee;
import com.eidiko.repository.EmployeeRepository;

@Service
public class ReportExcelService {
	@Autowired
	EmployeeRepository empRepo;

	public void generateExcel(HttpServletResponse response) throws IOException {
		List<Employee> listEmp = empRepo.findAll();

		System.out.println(listEmp.toString());

		HSSFWorkbook workbook = new HSSFWorkbook();

		HSSFSheet sheet = workbook.createSheet("employee");
		HSSFRow row = sheet.createRow(0);

		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Salary");
		row.createCell(3).setCellValue("City");
		int dataRowIndex = 1;

		for (Employee employee : listEmp) {
			HSSFRow dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(employee.getEmpId());
			dataRow.createCell(1).setCellValue(employee.getEmpName());
			dataRow.createCell(2).setCellValue(employee.getSalary());
			dataRow.createCell(3).setCellValue(employee.getCity());
			dataRowIndex++;
		}

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}