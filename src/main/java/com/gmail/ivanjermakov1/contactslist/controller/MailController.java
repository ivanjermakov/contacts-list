package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Mail;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidMailException;
import com.gmail.ivanjermakov1.contactslist.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MailController {
	
	private final MailService mailService;
	
	@Autowired
	public MailController(MailService mailService) {
		this.mailService = mailService;
	}
	
	@RequestMapping("mail/init")
	@GetMapping
	public Mail init() {
		return new Mail();
	}
	
	@RequestMapping("mail/send")
	@PostMapping
	public void send(@RequestBody Mail mail) throws InvalidMailException {
		if (!mail.isValid()) throw new InvalidMailException("invalid mail format.");
		mailService.sendMail(mail);
	}
	
}
