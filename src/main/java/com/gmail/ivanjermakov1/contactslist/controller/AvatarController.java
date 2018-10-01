package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import com.gmail.ivanjermakov1.contactslist.exception.EntityNotFoundException;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAvatarException;
import com.gmail.ivanjermakov1.contactslist.service.AvatarService;
import com.gmail.ivanjermakov1.contactslist.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;

import static com.gmail.ivanjermakov1.contactslist.service.UploadService.Type.AVATAR;

@RestController
public class AvatarController {
	
	private final AvatarService avatarService;
	private final UploadService uploadService;
	
	@Autowired
	public AvatarController(AvatarService avatarService, UploadService uploadService) {
		this.avatarService = avatarService;
		this.uploadService = uploadService;
	}
	
	@RequestMapping("avatar/init")
	@GetMapping
	public Avatar init() {
		return new Avatar();
	}
	
	@RequestMapping("avatar/insert")
	@PostMapping
	public void insert(@RequestBody Avatar avatar) throws SQLException, InvalidAvatarException {
		avatarService.insert(avatar);
	}
	
	@RequestMapping("avatar/select")
	@GetMapping
	public Avatar select(@RequestParam("id") int id) throws SQLException, EntityNotFoundException {
		return avatarService.select(id);
	}
	
	@RequestMapping("avatar/upload")
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		return uploadService.upload(multipartFile, AVATAR);
	}
	
}
