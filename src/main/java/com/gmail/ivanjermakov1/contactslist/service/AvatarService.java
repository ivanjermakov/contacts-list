package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import com.gmail.ivanjermakov1.contactslist.exception.EntityNotFoundException;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAvatarException;
import com.gmail.ivanjermakov1.contactslist.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AvatarService {
	
	private final AvatarRepository avatarRepository;
	
	@Autowired
	public AvatarService(AvatarRepository avatarRepository) {
		this.avatarRepository = avatarRepository;
	}
	
	public void insert(Avatar avatar) throws InvalidAvatarException, SQLException {
		if (!avatar.valid()) throw new InvalidAvatarException();
		avatarRepository.insert(avatar);
	}
	
	public Avatar select(int id) throws SQLException, EntityNotFoundException {
		return avatarRepository.select(id);
	}
	
}
