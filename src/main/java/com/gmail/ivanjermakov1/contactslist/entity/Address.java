package com.gmail.ivanjermakov1.contactslist.entity;

public class Address {
	
	private Integer contactId;
	private String country;
	private String region;
	private String locality;
	private Integer postcode;
	private Boolean removed;
	
	public Address() {
	}
	
	public Address(Integer contactId, String country, String region, String locality, Integer postcode, Boolean removed) {
		this.contactId = contactId;
		this.country = country;
		this.region = region;
		this.locality = locality;
		this.postcode = postcode;
		this.removed = removed;
	}
	
	public Integer getContactId() {
		return contactId;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getRegion() {
		return region;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public Integer getPostcode() {
		return postcode;
	}
	
	public Boolean getRemoved() {
		return removed;
	}
	
	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
	
	public boolean valid() {
		return contactId != null;
	}
	
}
