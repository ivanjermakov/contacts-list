package com.gmail.ivanjermakov1.contactslist.task;

import com.gmail.ivanjermakov1.contactslist.repository.ContactRepository;
import com.gmail.ivanjermakov1.contactslist.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

@Component
public class ScheduledTasks {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ContactRepository contactRepository;
	private final MailService mailService;
	
	@Autowired
	public ScheduledTasks(ContactRepository contactRepository, MailService mailService) {
		this.contactRepository = contactRepository;
		this.mailService = mailService;
	}
	
	@Scheduled(cron = "0 0 0 * * *")
	public void happyBirthday() throws SQLException {
		logger.info("congratulate with birthday");
		
		Date current = new Date(Calendar.getInstance().getTimeInMillis());
		
		contactRepository.selectWithBirthday(current).stream()
				.filter(c -> c.getEmail() != null)
				.forEach(mailService::sendBirthdayMail);
	}
	
}
