package com.eidiko.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.service.IEmployeeService;
import com.eidiko.service.ReportExcelService;

@RestController
@RequestMapping("/excel")
public class ReportController {

	@Autowired
	private ReportExcelService reportService;

	@Autowired
	private IEmployeeService empService;

	@GetMapping("/download")
	public void generateReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/vnd.ms-excel");

		String headerKey = "Content-Disposition";

		String headerValue = "attachment;filename=emp.xls";

		response.setHeader(headerKey, headerValue);
		reportService.generateExcel(response);
	}

	@PostMapping("/uploadEmployeesFile")
	public ResponseEntity<?> uploadEmployeesData(@RequestParam("file") MultipartFile file) {
		this.empService.saveEmployeesToDatabase(file);
		System.out.println("Inside excel reder controller");
		return ResponseEntity.ok(Map.of("Message", " Employees data uploaded and saved to database successfully"));
	}

}
