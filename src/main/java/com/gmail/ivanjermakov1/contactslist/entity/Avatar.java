package com.gmail.ivanjermakov1.contactslist.entity;

public class Avatar {
	
	private Integer contactId;
	private String path;
	
	public Avatar() {
	}
	
	public Avatar(Integer contactId, String path) {
		this.contactId = contactId;
		this.path = path;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	
	public String getPath() {
		return path;
	}
	
	public boolean valid() {
		return contactId != null;
	}
	
}
