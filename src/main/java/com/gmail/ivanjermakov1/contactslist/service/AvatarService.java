package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Avatar;
import com.gmail.ivanjermakov1.contactslist.exception.EntityNotFoundException;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAvatarException;
import com.gmail.ivanjermakov1.contactslist.repository.AvatarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AvatarService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final AvatarRepository avatarRepository;
	
	@Autowired
	public AvatarService(AvatarRepository avatarRepository) {
		this.avatarRepository = avatarRepository;
	}
	
	public void insert(Avatar avatar) throws InvalidAvatarException, SQLException {
		logger.info("insert avatar: of@" + avatar.getContactId());
		if (!avatar.valid()) throw new InvalidAvatarException();
		try {
			avatarRepository.select(avatar.getContactId());
			avatarRepository.edit(avatar);
		} catch (EntityNotFoundException e) {
			avatarRepository.insert(avatar);
		}
	}
	
	public Avatar select(int id) throws SQLException, EntityNotFoundException {
		logger.info("select avatar: of@" + id);
		return avatarRepository.select(id);
	}
	
}
