package com.gmail.ivanjermakov1.contactslist.entity;

public class Address {
	
	private Integer contactId;
	private String country;
	private String region;
	private String locality;
	private Integer postcode;
	
	public Address() {
	}
	
	public Address(Integer contactId, String country, String region, String locality, Integer postcode) {
		this.contactId = contactId;
		this.country = country;
		this.region = region;
		this.locality = locality;
		this.postcode = postcode;
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
	
	public boolean valid() {
		return contactId != null;
	}
	
}
