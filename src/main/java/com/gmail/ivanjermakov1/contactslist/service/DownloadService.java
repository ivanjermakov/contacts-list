package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

@Service
public class DownloadService {
	
	private final AttachmentService attachmentService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${upload.placeholder}")
	private String uploadPlaceholder;
	
	@Value("${web.resources}")
	private String webResources;
	
	@Autowired
	public DownloadService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	public File download(int attachmentId) throws SQLException, NoSuchEntityException, IOException {
		logger.info("download attachment: @" + attachmentId);
		
		Attachment attachment = attachmentService.select(attachmentId);
		
		return new File(uploadPlaceholder + attachment.getPath().substring(webResources.length()));
	}
	
}
