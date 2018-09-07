package com.gmail.ivanjermakov1.contactslist.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {
	
	public enum Type {
		ATTACHMENT("attachments/"),
		AVATAR("avatars/");
		
		private String path;
		
		Type(String path) {
			this.path = path;
		}
	}
	
	@Value("${upload.placeholder}")
	private String uploadPlaceholder;
	
	@Value("${web.resources}")
	private String webResources;
	
	public String getUploadPlaceholder() {
		return uploadPlaceholder;
	}
	
	public String getWebResources() {
		return webResources;
	}
	
	public void upload(MultipartFile multipartFile, Type type) throws IOException {
		String realPath = uploadPlaceholder + type.path;
		new File(realPath).mkdirs();
		
		File file = new File(realPath + "/" + multipartFile.getOriginalFilename());
		multipartFile.transferTo(file);
	}
	
}
