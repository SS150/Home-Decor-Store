package com.model;

public class ContactValid {
	
	public boolean isValidContact(String contact) {
		if (contact.matches("\\d{10}")) {
			return true;
		}
		return false;
	}

}
