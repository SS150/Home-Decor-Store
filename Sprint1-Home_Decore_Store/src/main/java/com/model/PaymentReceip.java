package com.model;

import java.util.Date;

public class PaymentReceip {
	
	private String customer_Name;
	private String item_Name;
	private Date payementAt;
	private long amount;
	
	
	public PaymentReceip(String customer_Name, String item_Name, Date payementAt, long amount) {
		super();
		this.customer_Name = customer_Name;
		this.item_Name = item_Name;
		this.payementAt = payementAt;
		this.amount = amount;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getItem_Name() {
		return item_Name;
	}
	public void setItem_Name(String item_Name) {
		this.item_Name = item_Name;
	}
	public Date getPayementAt() {
		return payementAt;
	}
	public void setPayementAt(Date payementAt) {
		this.payementAt = payementAt;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

	
}
