package com.model;

import javax.persistence.CascadeType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	
	@NotNull
	@Size(min = 2,max = 30,message = "Enter Name minimun 2 charector and maximun 30")
	private String name;
	
	@NotNull
	@Size(min = 2 ,max = 10,message ="User Name Must be minimun 2 and maximum 10")
	private String userName;
	
	@NotNull
	@Size(min = 2 ,max = 10,message ="Password Must be minimun 2 and maximum 10")
	private String password;
	
	@Email(message = "Email must be valid")
	private String email;
	
	@NotNull
	@Size(max = 10,min = 10,message = "Please Enter Valid Contact Number")
	private String contact;
	
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId",nullable = false)
	@JsonIgnore
	private Role role;
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String pName) {
		this.name = pName;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
//	public int getStatusId() {
//		return statusId;
//	}
//	public void setStatusId(int statusId) {
//		this.statusId = statusId;
//	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role roleId) {
		this.role = roleId;
	}
	
	

}
