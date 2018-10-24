package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Mail;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidMailException;
import com.gmail.ivanjermakov1.contactslist.service.MailService;
import com.gmail.ivanjermakov1.contactslist.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class MailController {
	
	private final MailService mailService;
	private final TemplateService templateService;
	
	@Autowired
	public MailController(MailService mailService, TemplateService templateService) {
		this.mailService = mailService;
		this.templateService = templateService;
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
	
	@RequestMapping("mail/template/init")
	@GetMapping
	public Map<String, String> selectAllTemplates() {
		return templateService.selectAllTemplates();
	}
	
	@RequestMapping("mail/template/send")
	@PostMapping
	public void sendTemplateMails(@RequestBody List<Integer> ids, @RequestParam("template") String templateName) {
		templateService.sendTemplateMails(templateName, ids);
	}
	
}
