package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Contact;
import com.gmail.ivanjermakov1.contactslist.entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		logger.info("send mail: to: " + mail.getTo());
		Properties props = new Properties();
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", starttls);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(props,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		
		try {
			MimeMessage message = new MimeMessage(session);
			
			mail.setFrom(username);
			
			message.setHeader("Content-Type", "text.txt/plain; charset=UTF-8");
			message.setFrom(new InternetAddress(mail.getFrom()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail.getTo()));
			message.setSubject(mail.getSubject(), "UTF-8");
			message.setText(mail.getText(), "UTF-8");
			
			Transport.send(message);
		} catch (MessagingException e) {
			logger.error("error sending mail: to: " + mail.getFrom());
			throw new RuntimeException(e);
		}
	}
	
	public void sendBirthdayMail(Contact contact) {
		logger.info("congratulate with birthday: @" + contact.getId());
		
		String topic = "Happy Birthday, " + contact.getName() + "!";
		String text = "Dear " + contact.getName() + " " + contact.getSurname() + "!\n"
				+ "Our app is sincerely congratulates you with Happy Birthday! \uD83C\uDF81\n" +
				"Yours, Contacts List.";
		sendMail(new Mail(null, contact.getEmail(), topic, text));
	}
	
}
