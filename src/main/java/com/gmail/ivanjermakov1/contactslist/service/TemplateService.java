package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Mail;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemplateService {
	
	private final ContactService contactService;
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, StringTemplate> templateMap;
	private final MailService mailService;
	
	@Autowired
	public TemplateService(ContactService contactService, MailService mailService) {
		this.contactService = contactService;
		this.mailService = mailService;
	}
	
	public Map<String, StringTemplate> selectAll() {
		logger.info("select all templates");
		return templateMap;
	}
	
	@PostConstruct
	private void init() {
		logger.info("initialize templates");
		
		templateMap = new LinkedHashMap<>();
		
		templateMap.put("Автоответчик",
				new StringTemplate("Здравствуйте! Спасибо за ваше сообщение. Меня не будет в офисе до 27 августа 2019. В течение этого периода у меня будет ограниченный доступ к электронной почте. По любым срочным вопросам, вы можете связаться со мной по номеру +7 44 310 3033 8 или по электронной почте XXX@jobijoba.com.\n" +
						"\n" +
						"С уважением,\n" +
						"\n" +
						"$name$", DefaultTemplateLexer.class));
		templateMap.put("Автоответчик 2",
				new StringTemplate("Меня нет в офисе, так как я прохожу собеседование при приеме на работу и я отвечу вам, если меня не возьмут. Будьте готовы к моему настроению", DefaultTemplateLexer.class));
		templateMap.put("День рождения",
				new StringTemplate("С днем рождения, $name$!", DefaultTemplateLexer.class));
	}
	
	public Map<String, String> selectAllTemplates() {
		return selectAll().entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().toString()));
	}
	
	public void sendTemplateMails(String templateName, List<Integer> ids) {
		StringTemplate template = templateMap.get(templateName);
		
		ids.stream()
				.map(id -> {
					try {
						return contactService.selectById(id);
					} catch (Exception e) {
						return null;
					}
				})
				.filter(c -> c != null && c.getEmail() != null)
				.forEach(c -> {
					template.reset();
					template.setAttribute("name", c.getName());
					mailService.sendMail(new Mail(null, c.getEmail(), "", template.toString()));
				});
	}
	
}
