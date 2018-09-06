package com.gmail.ivanjermakov1.contactslist.entity;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Avatar implements Insertable {
	
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
	
	@Override
	public PreparedStatement insert(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"avatar(contact_id, path) " +
						"values (?, ?)"
		);
		statement.setInt(1, contactId);
		statement.setString(2, path);
		
		return statement;
	}
	
}
