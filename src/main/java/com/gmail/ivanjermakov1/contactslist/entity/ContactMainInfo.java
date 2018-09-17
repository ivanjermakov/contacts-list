package com.gmail.ivanjermakov1.contactslist.entity;

import java.sql.Date;

public class ContactMainInfo {
	
	private int id;
	private String name;
	private String surname;
	private String patronymic;
	private Date birth;
	private String locality;
	private String workplace;
	
	public ContactMainInfo() {
	}
	
	public ContactMainInfo(int id, String name, String surname, String patronymic, Date birth, String locality, String workplace) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birth = birth;
		this.locality = locality;
		this.workplace = workplace;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPatronymic() {
		return patronymic;
	}
	
	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public String getWorkplace() {
		return workplace;
	}
	
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	
}
