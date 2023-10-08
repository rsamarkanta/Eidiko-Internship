package com.eidiko.service;

import org.springframework.web.multipart.MultipartFile;

import com.eidiko.entity.EmailRequest;

public interface EmailService {

	public String sendTemplateEmailWithAttachment(EmailRequest emailRequest);

	public String sendMailWithAttachment(EmailRequest emailRequest, MultipartFile[] files);

}
