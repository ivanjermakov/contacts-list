package com.gmail.ivanjermakov1.contactslist.entity;

import com.gmail.ivanjermakov1.contactslist.util.db.Insertable;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Contact implements Insertable {
	
	private String name;
	private String surname;
	private String patronymic;
	private Date birth;
	private Boolean sex;
	private String nationality;
	private String maritalStatus;
	private String webSite;
	private String email;
	private String jobPlace;
	private String country;
	private String city;
	private String address;
	private Integer postcode;
	
	public Contact() {
	}
	
	public Contact(String name, String surname, String patronymic, Date birth, Boolean sex, String nationality, String maritalStatus, String webSite, String email, String jobPlace, String country, String city, String address, Integer postcode) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birth = birth;
		this.sex = sex;
		this.nationality = nationality;
		this.maritalStatus = maritalStatus;
		this.webSite = webSite;
		this.email = email;
		this.jobPlace = jobPlace;
		this.country = country;
		this.city = city;
		this.address = address;
		this.postcode = postcode;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public Boolean getSex() {
		return sex;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public String getWebSite() {
		return webSite;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getJobPlace() {
		return jobPlace;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Integer getPostcode() {
		return postcode;
	}
	
	public boolean isValid() {
		return name != null && !name.isEmpty() &&
				surname != null && !surname.isEmpty();
	}
	
	@Override
	public PreparedStatement insert(Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"insert into " +
						"contacts(name, surname, patronymic, birth, sex, nationality, marital_status, web_site, email, job_place, country, city, address, postcode) " +
						"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
		);
		statement.setString(1, name);
		statement.setString(2, surname);
		statement.setString(3, patronymic);
		statement.setDate(4, birth);
		statement.setBoolean(5, sex);
		statement.setString(6, nationality);
		statement.setString(7, maritalStatus);
		statement.setString(8, webSite);
		statement.setString(9, email);
		statement.setString(10, jobPlace);
		statement.setString(11, country);
		statement.setString(12, city);
		statement.setString(13, address);
		statement.setInt(14, postcode);
		
		return statement;
	}
	
}
