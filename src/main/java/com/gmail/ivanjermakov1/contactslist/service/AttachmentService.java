package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAttachmentException;
import com.gmail.ivanjermakov1.contactslist.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

@Service
public class AttachmentService {
	
	private final Repository<Attachment> attachmentRepository;
	
	@Autowired
	public AttachmentService(Repository<Attachment> attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
	}
	
	public void add(Attachment attachment) throws InvalidAttachmentException, SQLException {
		if (!attachment.valid()) throw new InvalidAttachmentException();
		
		attachment.setUploaded(new Date(Calendar.getInstance().getTimeInMillis()));
		attachmentRepository.add(attachment);
	}
	
}
