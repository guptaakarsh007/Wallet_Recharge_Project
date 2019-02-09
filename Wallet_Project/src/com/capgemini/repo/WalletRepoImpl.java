package com.capgemini.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.beans.Customer;


public class WalletRepoImpl implements WalletRepo {
	
	Map<String,Customer> cust=new HashMap<String,Customer>();
	
	public boolean save(Customer custo)
	{
		cust.put(custo.getMobileno(),custo);
		return true;
	}

	@Override
	public Customer findOne(String mobileno) {
		if(cust.containsKey(mobileno))
		{
			Customer c=cust.get(mobileno);
			return c;
		}
		return null;
	}
	
}
