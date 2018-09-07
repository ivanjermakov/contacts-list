package com.gmail.ivanjermakov1.contactslist.entity;

import java.sql.Date;

public class Contact {
	
	private Integer id;
	private String name;
	private String surname;
	private String patronymic;
	private Boolean sex;
	private Date birth;
	private String nationality;
	private String maritalStatus;
	
	public Contact() {
	}
	
	public Contact(Integer id, String name, String surname, String patronymic, Boolean sex, Date birth,
	               String nationality, String maritalStatus) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.sex = sex;
		this.birth = birth;
		this.nationality = nationality;
		this.maritalStatus = maritalStatus;
	}
	
	public Integer getId() {
		return id;
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
	
	public Boolean getSex() {
		return sex;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public boolean valid() {
		return name != null && !name.isEmpty() &&
				surname != null && !surname.isEmpty();
	}
	
}
