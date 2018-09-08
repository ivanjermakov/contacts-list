package com.gmail.ivanjermakov1.contactslist.config;

import com.gmail.ivanjermakov1.contactslist.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebFileConfig implements WebMvcConfigurer {
	
	private final UploadService uploadService;
	
	@Autowired
	public WebFileConfig(UploadService uploadService) {
		this.uploadService = uploadService;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
				.addResourceHandler("/" + uploadService.getWebResources() + "**")
				.addResourceLocations("file:/" + uploadService.getUploadPlaceholder());
	}
	
}
