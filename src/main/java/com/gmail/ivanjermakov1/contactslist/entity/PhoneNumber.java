package com.gmail.ivanjermakov1.contactslist.entity;

public class PhoneNumber {
	
	private Integer contactId;
	private Integer areaCode;
	private Integer operatorCode;
	private Integer number;
	private Boolean type;
	private String comment;
	
	public PhoneNumber() {
	}
	
	public PhoneNumber(Integer contactId, Integer areaCode, Integer operatorCode, Integer number, Boolean type, String comment) {
		this.contactId = contactId;
		this.areaCode = areaCode;
		this.operatorCode = operatorCode;
		this.number = number;
		this.type = type;
		this.comment = comment;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	
	public Integer getAreaCode() {
		return areaCode;
	}
	
	public Integer getOperatorCode() {
		return operatorCode;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public Boolean getType() {
		return type;
	}
	
	public String getComment() {
		return comment;
	}
	
	public boolean valid() {
		return contactId != null &&
				areaCode != null &&
				operatorCode != null &&
				number != null &&
				type != null;
	}
	
}
