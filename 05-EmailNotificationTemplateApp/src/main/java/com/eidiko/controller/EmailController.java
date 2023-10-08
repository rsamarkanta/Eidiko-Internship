package com.eidiko.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.EmailRequest;
import com.eidiko.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/sendMailTemplate")
	public ResponseEntity<Map<String, Object>> sendTemplateEmail(@Valid @ModelAttribute EmailRequest emailRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Check for Subject,To and MailBody may be missing");
		}

//		try {
		String result = emailService.sendTemplateEmailWithAttachment(emailRequest);
//			return ResponseEntity.ok("Email sent successfully.");
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//					.body("Error sending email: " + e.getMessage());
//		}

		Map<String, Object> response = new HashMap<>();
		response.put("Result", "Success");
		response.put("message", result);
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	@PostMapping("/sendMailWithAttachment")
	public ResponseEntity<Map<String, Object>> sendMailWithAttachment(@Valid EmailRequest emailRequest,
			BindingResult bindingResult, @RequestParam(value = "file", required = false) MultipartFile[] file) {
		System.out.println("EmailController.sendMailWithAttachment()---1");

		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Check for Subject,To and MailBody may be missing");
		}
		
		
		System.out.println("EmailController.sendMailWithAttachment()---2");
		String result = emailService.sendMailWithAttachment(emailRequest, file);
		System.out.println("EmailController.sendMailWithAttachment()----3");
		Map<String, Object> response = new HashMap<>();
		response.put("Result", result);
		response.put("message", "Success");
		response.put("status", HttpStatus.OK.value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
