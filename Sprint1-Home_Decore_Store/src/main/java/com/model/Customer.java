package com.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class Customer extends Person {
	@NotNull
	@Size(max = 30,min = 2,message = "Please Enter Valid Address")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}
