package com.gmail.ivanjermakov1.contactslist.controller;

import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAvatarException;
import com.gmail.ivanjermakov1.contactslist.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class AvatarController {
	
	private final AvatarService avatarService;
	
	@Autowired
	public AvatarController(AvatarService avatarService) {
		this.avatarService = avatarService;
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
	
}
