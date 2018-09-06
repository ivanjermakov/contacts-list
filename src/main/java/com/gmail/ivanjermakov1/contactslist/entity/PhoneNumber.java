package com.gmail.ivanjermakov1.contactslist.entity;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneNumber implements Insertable {
	
	private String name;
	private String surname;
	private Integer area;
	private Integer operator;
	private Integer number;
	private Boolean type;
	private String comment;
	
	public PhoneNumber() {
	}
	
	public PhoneNumber(String name, String surname, Integer area, Integer operator, Integer number, Boolean type, String comment) {
		this.name = name;
		this.surname = surname;
		this.area = area;
		this.operator = operator;
		this.number = number;
		this.type = type;
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public Integer getArea() {
		return area;
	}
	
	public Integer getOperator() {
		return operator;
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
	
	public boolean isValid() {
		return name != null && !name.isEmpty() &&
				surname != null && !surname.isEmpty() &&
				area != null &&
				operator != null &&
				number != null &&
				type != null;
	}
	
	@Override
	public PreparedStatement insert(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"phone_numbers(name, surname, area, operator, number, type, comment) " +
						"values (?, ?, ?, ?, ?, ?, ?)"
		);
		statement.setString(1, name);
		statement.setString(2, surname);
		statement.setInt(3, area);
		statement.setInt(4, operator);
		statement.setInt(5, number);
		statement.setBoolean(6, type);
		statement.setString(7, comment);
		
		return statement;
	}
	
}
