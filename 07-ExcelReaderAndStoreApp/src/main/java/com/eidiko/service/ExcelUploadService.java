package com.eidiko.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.Employee;
import com.eidiko.exception.InvalidFileFormatException;

public class ExcelUploadService {
	public static boolean isValidExcelFile(MultipartFile file) {

		System.out.println(file.getContentType());
		System.out.println("Inside excel reder file content check");
		if (Objects.equals(file.getContentType(), "application/vnd.ms-excel")) {
			return true;
		} else if (Objects.equals(file.getContentType(), "application/octet-stream")) {
			return true;
		} else if (Objects.equals(file.getContentType(),
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else
			throw new InvalidFileFormatException("Accept only excel file");
	}

	public static List<Employee> getCustomersDataFromExcel(InputStream inputStream) {

		System.out.println("Inside excel reder");
		List<Employee> empList = new ArrayList<>();
		try (HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rowIndex = 0;
			for (Row row : sheet) {
				if (rowIndex == 0) {
					rowIndex++;
					continue;
				}
				Iterator<Cell> cellIterator = row.iterator();
				int cellIndex = 0;
				Employee emp = new Employee();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch (cellIndex) {
					case 0 -> emp.setEmpId((long) cell.getNumericCellValue());
					case 1 -> emp.setEmpName(cell.getStringCellValue());
					case 2 -> emp.setSalary((long) cell.getNumericCellValue());
					case 3 -> emp.setCity(cell.getStringCellValue());

					default -> {
					}
					}
					cellIndex++;
				}
				empList.add(emp);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		return empList;
	}

}
