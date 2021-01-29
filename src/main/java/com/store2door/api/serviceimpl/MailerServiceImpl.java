package com.store2door.api.serviceimpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.store2door.api.dto.EmailDetailsDTO;
import com.store2door.api.model.User;
import com.store2door.api.service.MailerService;
import com.store2door.api.utils.Constants;
import com.store2door.api.utils.JString;



@Service
public class MailerServiceImpl implements MailerService {
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	  private String fromAddress;
	@Value("${app.mail_to_Address}")
	private String toMailsAddress;
	
	public void sendEmailOnException(String environment,String subject,Exception exception){
		EmailDetailsDTO emailDetailsDTO=new EmailDetailsDTO();
		try {
			StringWriter sw = new StringWriter();
			exception.printStackTrace(new PrintWriter(sw));
			String[] str = toMailsAddress.split(",");
			emailDetailsDTO.setToAddress(str);
			emailDetailsDTO.setSubject(subject);
			emailDetailsDTO.setEmailBody("Environment:"+environment+"\n\n\n\n\nException:"+sw.toString());
			sendMail(emailDetailsDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendEmailTemplate(String toAddress,String subject,String emailHtml)throws Exception{
		EmailDetailsDTO emailDetailsDTO=new EmailDetailsDTO();
		String[] str = new String[1] ;
		try {
			str[0]=toAddress;
			emailDetailsDTO.setToAddress(str);
			emailDetailsDTO.setSubject(subject);
			emailDetailsDTO.setEmailBody(emailHtml);
			sendHTMLMail(emailDetailsDTO);
		}catch (Exception e) {
			throw e;
		}
	}
	public void sendMail(EmailDetailsDTO emailDetailsDTO) throws MailException, Exception {
		try {
			if (!JString.isEmpty(emailDetailsDTO)) {
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom(fromAddress);
				message.setText(!JString.isEmpty(emailDetailsDTO.getEmailBody()) ? emailDetailsDTO.getEmailBody() : "");
				message.setSubject(!JString.isEmpty(emailDetailsDTO.getSubject()) ? emailDetailsDTO.getSubject() : "");
				if (!JString.isEmpty(emailDetailsDTO.getToAddress())) {
					message.setTo(emailDetailsDTO.getToAddress());
					if (!JString.isEmpty(emailDetailsDTO.getcCAddress())) {
						message.setCc(emailDetailsDTO.getcCAddress());
					}
					if (!JString.isEmpty(emailDetailsDTO.getbCCAddress())) {
						message.setBcc(emailDetailsDTO.getbCCAddress());
					}

				} else {
					message.setTo(toMailsAddress.split(","));
					message.setSubject(Constants.EMAIL_MESSAGE.getValue());
				}

				javaMailSender.send(message);
			}
		}catch (Exception e) {
			throw e;
		}
	}
	public void sendHTMLMail(EmailDetailsDTO emailDetailsDTO) throws MailException, Exception {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, Constants.UTF_8_FORMAT_CONSTANT.getValue());
			if (!JString.isEmpty(emailDetailsDTO)) {
				mimeMessage.setContent(!JString.isEmpty(emailDetailsDTO.getEmailBody()) ? emailDetailsDTO.getEmailBody() : "", "text/html");
				helper.setFrom(fromAddress);
				helper.setSubject(!JString.isEmpty(emailDetailsDTO.getSubject()) ? emailDetailsDTO.getSubject() : "");
				if (!JString.isEmpty(emailDetailsDTO.getToAddress())) {
					helper.setTo(emailDetailsDTO.getToAddress());
					if (!JString.isEmpty(emailDetailsDTO.getcCAddress())) {
						helper.setCc(emailDetailsDTO.getcCAddress());
					}
					if (!JString.isEmpty(emailDetailsDTO.getbCCAddress())) {
						helper.setBcc(emailDetailsDTO.getbCCAddress());
					}

				} else {
					helper.setTo(toMailsAddress.split(","));
					helper.setSubject("TO Address Is Empty");
				}

				javaMailSender.send(mimeMessage);
			}
		} catch (MailException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}
	@Override
	public String getEmailTemplateForWelcome(User user) throws Exception {
		String emailHtml = "";
		try {
			FileReader read = new FileReader(Constants.FORGOTPASSWORDTEMPLATE.getValue());
			BufferedReader buffer = new BufferedReader(read);
			String line = "";
			while ((line = buffer.readLine()) != null) {
				emailHtml += (line + "\n");
			}
			read.close();
			emailHtml = emailHtml.replaceFirst("@userName@",user.getName().toUpperCase());
			
			//emailHtml = emailHtml.replaceFirst("@userId@","<p class=\"summary\" style=\" color:#000000;\">\r\n<b>User Name: </b>"+signUpRequest.getUsername()+"</p>");
			emailHtml = emailHtml.replaceFirst("@password@","<p class=\"summary\" style=\" color:#000000;\">\r\n<b>&nbsp;Password: </b> "+user.getPassword()+"</p>");	
			
		} catch (Exception exception) {
			throw exception;
		}
		return emailHtml;
	}
}