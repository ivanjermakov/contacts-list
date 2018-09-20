package com.gmail.ivanjermakov1.contactslist.entity;

import java.sql.Date;

public class Search {
	
	private String name;
	private String surname;
	private String patronymic;
	
	private Date birth;
	private Boolean isBirthAfter;
	
	private Boolean sex;
	private String maritalStatus;
	private String nationality;
	
	private String country;
	private String region;
	private String locality;
	private Integer postcode;
	
	public Search() {
	}
	
	public Search(String name, String surname, String patronymic, Date birth, Boolean isBirthAfter, Boolean sex, String maritalStatus, String nationality, String country, String region, String locality, Integer postcode) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.birth = birth;
		this.isBirthAfter = isBirthAfter;
		this.sex = sex;
		this.maritalStatus = maritalStatus;
		this.nationality = nationality;
		this.country = country;
		this.region = region;
		this.locality = locality;
		this.postcode = postcode;
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
	
	public Boolean getBirthAfter() {
		return isBirthAfter;
	}
	
	public void setBirthAfter(Boolean birthAfter) {
		isBirthAfter = birthAfter;
	}
	
	public Boolean getSex() {
		return sex;
	}
	
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getRegion() {
		return region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}
	
	public Integer getPostcode() {
		return postcode;
	}
	
	public void setPostcode(Integer postcode) {
		this.postcode = postcode;
	}
	
}
