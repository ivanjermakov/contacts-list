package com.gmail.ivanjermakov1.contactslist.entity;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneNumber implements Insertable {
	
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
	
	@Override
	public PreparedStatement insert(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"phone_number(contact_id, area_code, operator_code, number, type, comment) " +
						"values (?, ?, ?, ?, ?, ?)"
		);
		statement.setInt(1, contactId);
		statement.setInt(2, areaCode);
		statement.setInt(3, operatorCode);
		statement.setInt(4, number);
		statement.setBoolean(5, type);
		statement.setString(6, comment);
		
		return statement;
	}
	
}
