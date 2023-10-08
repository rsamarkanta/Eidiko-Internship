package com.eidiko.service;

import java.util.Objects;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.eidiko.entity.EmailRequest;
import com.eidiko.exception.TemplateNotFoundException;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	@Value("${{spring.mail.username}")
	private String sender;

	@Override
	public String sendTemplateEmailWithAttachment(EmailRequest emailRequest) {

		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(emailRequest.getTo());
			helper.setSubject(emailRequest.getSubject());
			helper.setCc(emailRequest.getCc());

			// Create a Thymeleaf context and add variables
			Context thymeleafContext = new Context();
			thymeleafContext.setVariable("subject", emailRequest.getSubject());
			thymeleafContext.setVariable("name", emailRequest.getName()); // Example name
			thymeleafContext.setVariable("mailBody", emailRequest.getMailBody());

			// Process the HTML template with Thymeleaf
			String htmlContent = templateEngine.process("templete", thymeleafContext);

			if (htmlContent != null) {
				helper.setText(htmlContent, true);
			} else {
				throw new TemplateNotFoundException("Email template file not found or couldn't be read!");
			}

			if (emailRequest.getAttachment() != null && !emailRequest.getAttachment().isEmpty()) {
				helper.addAttachment(Objects.requireNonNull(emailRequest.getAttachment().getOriginalFilename()),
						new ByteArrayResource(emailRequest.getAttachment().getBytes()));
			}

			javaMailSender.send(message);
			return "Email Send Succesfully to " + emailRequest.getTo();
		} catch (Exception e) {
			e.printStackTrace();
			return "Email Sending  Unsuccesful";
		}

	}

	@Override
	public String sendMailWithAttachment(EmailRequest emailData, MultipartFile[] files) {
		try {
			MimeMessage mm = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mm, true);

			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(emailData.getTo());
			mimeMessageHelper.setText(emailData.getMailBody());
			mimeMessageHelper.setSubject(emailData.getSubject());

			if (files != null)
				for (MultipartFile file : files) {
					mimeMessageHelper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(file.getBytes()));
				}

			javaMailSender.send(mm);

			return "Mail with Attachment Sent Successfully !";
		} catch (Exception e) {
			return "Error in sending Mail with Attachment";
		}
	}
}
