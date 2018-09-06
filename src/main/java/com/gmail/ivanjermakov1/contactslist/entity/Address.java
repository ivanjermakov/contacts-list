package com.gmail.ivanjermakov1.contactslist.entity;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Address implements Insertable {
	
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
	
	@Override
	public PreparedStatement insert(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"address(contact_id, country, region, locality, postcode) " +
						"values (?, ?, ?, ?, ?)"
		);
		statement.setInt(1, contactId);
		statement.setString(2, country);
		statement.setString(3, region);
		statement.setString(4, locality);
		statement.setInt(5, postcode);
		
		return statement;
	}
	
}
