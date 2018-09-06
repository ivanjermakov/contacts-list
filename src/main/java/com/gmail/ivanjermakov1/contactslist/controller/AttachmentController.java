package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAttachmentException;
import com.gmail.ivanjermakov1.contactslist.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class AttachmentController {
	
	private final AttachmentService attachmentService;
	
	@Autowired
	public AttachmentController(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@RequestMapping("attachment/init")
	@GetMapping
	public Attachment init() {
		return new Attachment();
	}
	
	@RequestMapping("attachment/add")
	@PostMapping
	public void add(@RequestBody Attachment attachment) throws SQLException, InvalidAttachmentException {
		attachmentService.add(attachment);
	}
	
}
