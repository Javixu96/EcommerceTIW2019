package es.uc3m.ecommerce.controller;

/* Attributes based on fields of the Registration Form */

public class UserDataModelBean {
	
	private String name; 
	private String surname;
	private String address; 
	private String phone;
	private String email; 
	private String password; 
	
	public UserDataModelBean() {}
	
	public UserDataModelBean(String name, String surname, String address, String phone, String email, String password) {
		this.name = name;
		this.surname = surname; 
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getAdress() {
		return this.address;
	}
	
	public void setAddress1(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.address;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
