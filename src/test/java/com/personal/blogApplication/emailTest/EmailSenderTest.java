package com.personal.blogApplication.emailTest;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.personal.chronikale.ChroniKaleApplication;
import com.personal.chronikale.ServiceSAO.EmailServicesSAO;

@SpringBootTest
(classes =ChroniKaleApplication.class)



public class EmailSenderTest {
	@Autowired
	private EmailServicesSAO emailService;
	
//	@Test
	void sendEmailTest(){
		System.out.println("Sending Email...");
		emailService
		.sendEmail(
				"gautamchanda316@gmail.com", 
				"Test Email", 
				"This Email is sent while testing sendEmail Service from Spring Boot"
				);
		
	}
//	@Test
	void sendEmailWithHtmlTest() {
		System.out.println("Html Mail Sent !");
		String html=""+
		"<h1 style='color:blue;border=1px solid green;border-radius:0.5rem'>This email is sent from spring-boot application while testing HTML Content functionality</h1>";
		emailService.sendEmailWithHtml(
				"gautamchanda316@gmail.com",
				"HTML test Mail 2",
				html
				);
	}
	@Test
	void sendEmailWithFIleTest() {
		System.out.println("Mail with attachment is Sent !");
		emailService.sendEmailWithFIle(
				"gautamchanda316@gmail.com",
				"Test Mail with File",
				"This Email contains a file sent from Spring-boot application",
				new File("D:\\BackendProject\\blogApplication\\src\\main\\resources\\static\\TestFile.pdf")
				);
	}
	

}
