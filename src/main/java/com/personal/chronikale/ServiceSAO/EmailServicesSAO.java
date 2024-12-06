package com.personal.chronikale.ServiceSAO;

import java.io.File;

public interface EmailServicesSAO {
	// Send Email to single person
	void sendEmail(String to, String subject, String message);
	// send Email to multiple Person
	void sendEmail(String []to, String subject, String message);
	// Send Email with Html
	void sendEmailWithHtml(String to, String subject, String htmlContent);
	// Send Email with Attached File
	void sendEmailWithFIle(String to, String subject, String message, File file);
}
