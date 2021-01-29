package com.store2door.api.service;

import org.springframework.mail.MailException;

import com.store2door.api.dto.EmailDetailsDTO;
import com.store2door.api.model.User;

public interface MailerService {
	public void sendEmailOnException(String Environment, String subject, Exception exception);

	public void sendEmailTemplate(String toAddress, String subject, String emailHtml)throws Exception;

	public void sendMail(EmailDetailsDTO emailDetailsDTO) throws Exception;

	public void sendHTMLMail(EmailDetailsDTO emailDetailsDTO) throws MailException, Exception;
	
	public  String getEmailTemplateForWelcome(User user) throws Exception;

}
