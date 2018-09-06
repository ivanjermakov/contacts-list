package com.gmail.ivanjermakov1.contactslist.entity;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Attachment implements Insertable {
	
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
	
	@Override
	public PreparedStatement insert(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"attachment(contact_id, name, uploaded, path, comment) " +
						"values (?, ?, ?, ?, ?)"
		);
		statement.setInt(1, contactId);
		statement.setString(2, name);
		statement.setDate(3, uploaded);
		statement.setString(4, path);
		statement.setString(5, comment);
		
		return statement;
	}
	
}
