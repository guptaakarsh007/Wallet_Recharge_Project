package com.capgemini.beans;

import java.math.BigDecimal;

public class Customer {
	private String name;
	private String mobileno;
	private Wallet wallet;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public Wallet getWallet() {
		return wallet;
	}
	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
	public void put(String mobileno2, Customer cust) {
		
		
	}
	@Override
	public String toString() {
		return "Customer [name=" + name + ", mobileno=" + mobileno + ", wallet=" + wallet + "]";
	}
	
	
	

}
