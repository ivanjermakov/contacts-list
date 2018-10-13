package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAttachmentException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.service.AttachmentService;
import com.gmail.ivanjermakov1.contactslist.service.DownloadService;
import com.gmail.ivanjermakov1.contactslist.service.UploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import static com.gmail.ivanjermakov1.contactslist.service.UploadService.Type.ATTACHMENT;

@RestController
public class AttachmentController {
	
	private final AttachmentService attachmentService;
	private final UploadService uploadService;
	private final DownloadService downloadService;
	
	
	@Autowired
	public AttachmentController(AttachmentService attachmentService, UploadService uploadService, DownloadService downloadService) {
		this.attachmentService = attachmentService;
		this.uploadService = uploadService;
		this.downloadService = downloadService;
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
	
	@RequestMapping("attachment/edit")
	@PostMapping
	public void edit(@RequestBody Attachment attachment) throws SQLException, InvalidAttachmentException {
		attachmentService.edit(attachment);
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
	
	@RequestMapping("attachment/remove")
	@GetMapping
	public void removeById(@RequestParam("id") int id) throws SQLException {
		attachmentService.removeById(id);
	}
	
	@RequestMapping(value = "attachment/upload")
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		return uploadService.upload(multipartFile, ATTACHMENT);
	}
	
	@RequestMapping(value = "attachment/download")
	public ResponseEntity<Resource> download(@RequestParam("id") int id) throws SQLException, NoSuchEntityException, IOException {
		File file = downloadService.download(id);
		
		InputStreamResource resource;
		try {
			resource = new InputStreamResource(new FileInputStream(file));
		} catch (Exception e) {
			throw new NoSuchEntityException("invalid attachment.");
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + attachmentService.select(id).getName() + "." +
						FilenameUtils.getExtension(file.getPath()));
		return ResponseEntity.ok()
				.headers(headers)
				.contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(resource);
	}
	
}
