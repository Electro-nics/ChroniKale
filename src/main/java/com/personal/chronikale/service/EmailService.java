package com.personal.chronikale.service;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.personal.chronikale.ServiceSAO.EmailServicesSAO;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class EmailService implements EmailServicesSAO{
	@Autowired
	private JavaMailSender mailSender;
	private Logger logger= LoggerFactory.getLogger(EmailService.class);
	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage mailMessage= new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom("mailsender.testid@gmail.com");
		mailSender.send(mailMessage);
		logger.info("Email Has been sent");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailMessage.setFrom("mailsender.testid@gmail.com");
		mailSender.send(mailMessage);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent) {
		MimeMessage simpleMessage= mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper= new MimeMessageHelper(simpleMessage,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlContent, true);
			helper.setFrom("mailsender.testid@gmail.com");
			mailSender.send(simpleMessage);
			logger.info("Email sent Successfully !!");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public void sendEmailWithFIle(String to, String subject, String message, File file) {
		MimeMessage mimeMessage= mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage, true);
			helper.setTo(to);
			helper.setFrom("mailsender.testid@gmail.com");
			helper.setText(message);
			helper.setSubject(subject);
			FileSystemResource fileSystemResource=new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("Email Sent Successfully !!");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

}
