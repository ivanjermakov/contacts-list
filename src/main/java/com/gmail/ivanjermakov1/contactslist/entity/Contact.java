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
	private String website;
	private String email;
	private String workplace;
	private Boolean removed;
	
	public Contact() {
	}
	
	public Contact(Integer id, String name, String surname, String patronymic, Boolean sex, Date birth, String nationality, String maritalStatus, String website, String email, String workplace, Boolean removed) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.sex = sex;
		this.birth = birth;
		this.nationality = nationality;
		this.maritalStatus = maritalStatus;
		this.website = website;
		this.email = email;
		this.workplace = workplace;
		this.removed = removed;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
	
	public Boolean getSex() {
		return sex;
	}
	
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getWorkplace() {
		return workplace;
	}
	
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	
	public Boolean getRemoved() {
		return removed;
	}
	
	public void setRemoved(Boolean removed) {
		this.removed = removed;
	}
	
	public boolean valid() {
		return name != null && !name.isEmpty() &&
				surname != null && !surname.isEmpty();
	}
	
}
