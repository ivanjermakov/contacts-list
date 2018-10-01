package com.gmail.ivanjermakov1.contactslist.service;

import com.gmail.ivanjermakov1.contactslist.entity.Attachment;
import com.gmail.ivanjermakov1.contactslist.exception.InvalidAttachmentException;
import com.gmail.ivanjermakov1.contactslist.exception.NoSuchEntityException;
import com.gmail.ivanjermakov1.contactslist.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Set;

@Service
public class AttachmentService {
	
	private final AttachmentRepository attachmentRepository;
	
	@Autowired
	public AttachmentService(AttachmentRepository attachmentRepository) {
		this.attachmentRepository = attachmentRepository;
	}
	
	public void insert(Attachment attachment) throws InvalidAttachmentException, SQLException {
		if (!attachment.valid()) throw new InvalidAttachmentException();
		
		attachment.setUploaded(new Date(Calendar.getInstance().getTimeInMillis()));
		attachmentRepository.insert(attachment);
	}
	
	public void edit(Attachment attachment) throws SQLException, InvalidAttachmentException {
		if (!attachment.valid()) throw new InvalidAttachmentException();
		attachmentRepository.edit(attachment);
	}
	
	public Attachment select(int id) throws SQLException, NoSuchEntityException {
		return attachmentRepository.select(id);
	}
	
	public Set<Attachment> selectByContactId(int contactId) throws SQLException {
		return attachmentRepository.selectByContactId(contactId);
	}
	
	public void removeById(int id) throws SQLException {
		attachmentRepository.removeById(id);
	}
	
}
