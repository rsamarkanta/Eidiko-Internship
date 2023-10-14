package com.eidiko.service;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.eidiko.entity.Employee;
import com.eidiko.entity.Performance;
import com.eidiko.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;
	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Employee saveEmp(Employee emp) {
		return empRepo.save(emp);
	}

	@Override
//	@Scheduled(cron = "* * 10 10 * *")
	@Scheduled(cron = "10 * * * * *")
	public void sendEmailWithAttachment() {

		log.info("SendMailWithAttachment() called....");

		String subject = "Regarding Performance in Previous Month ";
		String content = "";
		List<Employee> empList = this.getAllTrainee();

		for (Employee empl : empList) {
			log.info("Printing each employee data");

			System.out.println(empl.getName());
		}

		for (Employee employee : empList) {
			log.info("find each employee data...");

			Performance performance = employee.getPerformanceList().get(0);

			log.info("find performnance for previous month from each employee data...");

			if (performance.getTechRating() < 2.5 && performance.getCommunicationRating() < 2.5) {
				content = "Your Technical Rating and Communicational Rating is low please Improve it."
						+ "\n Technical Rating :: " + performance.getTechRating() + "\n  Communicational Rating ::     "
						+ performance.getCommunicationRating();
			} else if (performance.getCommunicationRating() < 2.5) {
				content = "Your communication rating is low please improve it." + "\n Communicational Rating ::     "
						+ performance.getCommunicationRating();
			} else if (performance.getTechRating() < 2.5) {
				content = "your Techincal Rating is low please Improve it." + "\n Technical Rating :: "
						+ performance.getTechRating();
			}

			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setTo(employee.getEmail());
				helper.setSubject(subject);

				// Create a Thymeleaf context and add variables
				Context thymeleafContext = new Context();
				thymeleafContext.setVariable("subject", subject);
				thymeleafContext.setVariable("name", employee.getName()); // Example name
				thymeleafContext.setVariable("content", content);

				// Process the HTML template with Thymeleaf
				String htmlContent = templateEngine.process("templete", thymeleafContext);

				if (htmlContent != null) {
					helper.setText(htmlContent, true);
				} else {
					System.err.println("Email template file not found or couldn't be read!");

				}

				javaMailSender.send(message);
				log.info("SendMailWithAttachment() Execution completed....");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Employee> getAllTrainee() {

		log.info("getAllTrainee() called....");

		List<Employee> employee = empRepo.findEmployeesWithFilteredConditions();

		// System.out.println(employee);
		log.info("find filtered  employee data...from employeee Repository");

		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		System.out.println(month);
		List<Employee> emps = employee.stream().filter(
				emp -> emp.getPerformanceList().stream().anyMatch(performancce -> performancce.getMonth() == month))
				.collect(Collectors.toList());

		log.info("employee data filterd completed....");

		// System.out.println(employee.toString());
		log.info("getAllTrainee() Execution completed....");
		return emps;

	}

}
