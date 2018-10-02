package com.gmail.ivanjermakov1.contactslist.service;

import org.antlr.stringtemplate.StringTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TemplateService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Map<String, StringTemplate> templateMap;
	
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
						"Ваше имя "));
		templateMap.put("Автоответчик 2",
				new StringTemplate("Меня нет в офисе, так как я прохожу собеседование при приеме на работу и я отвечу вам, если меня не возьмут. Будьте готовы к моему настроению"));
		templateMap.put("День рождения",
				new StringTemplate("С днем рождения, Имя!"));
	}
	
}
