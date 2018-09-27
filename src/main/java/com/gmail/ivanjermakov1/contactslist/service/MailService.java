package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Mail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
	
	@Value("${mail.smtp.user}")
	private String username;
	@Value("${mail.smtp.password}")
	private String password;
	@Value("${mail.smtp.auth}")
	private String auth;
	@Value("${mail.smtp.starttls.enable}")
	private String starttls;
	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.smtp.port}")
	private String port;
	
	
	public void sendMail(Mail mail) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		try {
			MimeMessage message = new MimeMessage(session);
			
			mail.setFrom(username);
			
			message.setHeader("Content-Type", "text/plain; charset=UTF-8");
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail.getTo()));
			message.setSubject(mail.getSubject(), "UTF-8");
			message.setText(mail.getText(), "UTF-8");
			
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
