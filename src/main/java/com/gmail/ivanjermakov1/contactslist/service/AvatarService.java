package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAvatarException;
import com.gmail.ivanjermakov1.contactslist.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AvatarService {
	
	private final Repository<Avatar> avatarRepository;
	
	@Autowired
	public AvatarService(Repository<Avatar> avatarRepository) {
		this.avatarRepository = avatarRepository;
	}
	
	public void add(Avatar avatar) throws InvalidAvatarException, SQLException {
		if (!avatar.valid()) throw new InvalidAvatarException();
		avatarRepository.add(avatar);
	}
	
}
