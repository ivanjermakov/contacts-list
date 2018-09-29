package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAttachmentException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.service.AttachmentService;
import com.gmail.ivanjermakov1.contactslist.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import static com.gmail.ivanjermakov1.contactslist.service.UploadService.Type.ATTACHMENT;

@RestController
public class AttachmentController {
	
	private final AttachmentService attachmentService;
	private final UploadService uploadService;
	
	@Autowired
	public AttachmentController(AttachmentService attachmentService, UploadService uploadService) {
		this.attachmentService = attachmentService;
		this.uploadService = uploadService;
	}
	
	@RequestMapping("attachment/init")
	@GetMapping
	public Attachment init() {
		return new Attachment();
	}
	
	@RequestMapping("attachment/insert")
	@PostMapping
	public void insert(@RequestBody Attachment attachment) throws SQLException, InvalidAttachmentException {
		attachmentService.insert(attachment);
	}
	
	@RequestMapping("attachment/select")
	@GetMapping
	public Attachment select(@RequestParam("id") int id) throws SQLException, NoSuchEntityException {
		return attachmentService.select(id);
	}
	
	@RequestMapping("attachment/selectByContactId")
	@GetMapping
	public Set<Attachment> selectByContactId(@RequestParam("id") int contactId) throws SQLException {
		return attachmentService.selectByContactId(contactId);
	}
	
	@RequestMapping("attachment/upload")
	@PostMapping
	public void upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		uploadService.upload(multipartFile, ATTACHMENT);
	}
	
}
