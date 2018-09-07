package com.gmail.ivanjermakov1.contactslist.entity;

import java.sql.Date;

public class Attachment {
	
	private Integer contactId;
	private String name;
	private Date uploaded;
	private String path;
	private String comment;
	
	public Attachment() {
	}
	
	public Attachment(Integer contactId, String name, Date uploaded, String path, String comment) {
		this.contactId = contactId;
		this.name = name;
		this.uploaded = uploaded;
		this.path = path;
		this.comment = comment;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getUploaded() {
		return uploaded;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setUploaded(Date uploaded) {
		this.uploaded = uploaded;
	}
	
	public boolean valid() {
		return name != null && !name.isEmpty() &&
				path != null && !path.isEmpty();
	}
	
}
