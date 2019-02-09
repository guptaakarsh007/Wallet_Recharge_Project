package com.capgemini.repo;

import java.util.List;

import com.capgemini.beans.Customer;

public interface WalletRepo {
	public boolean save(Customer customer);
	public Customer findOne(String mobileno);

}
