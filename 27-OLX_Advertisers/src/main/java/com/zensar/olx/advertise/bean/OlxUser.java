package com.zensar.olx.advertise.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
@Embeddable

public class OlxUser{
	@Column(name="olx_user")
	private int userId;
	@Transient
	private String userName;
	//@Transient
	private String password;
	@Transient
	private String roles;
	@Transient
	private String firstName;
	@Transient
	private String lastName;
	@Transient
	private String email;
	@Transient
	private String phoneNumber;
	@Transient
	private Active status;
	@Override
	public String toString() {
		return "OlxUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", roles=" + roles
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", status=" + status + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Active getStatus() {
		return status;
	}
	public void setStatus(Active status) {
		this.status = status;
	}
	
}
