package com.capgemini.main;

import java.math.BigDecimal;

import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImpl;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

public class Main {

	public static void main(String[] args) {
		WalletRepo repo=new WalletRepoImpl();
		WalletService ser=new WalletServiceImpl(repo);
		System.out.println(ser.createAccount("akarsh","986632499", BigDecimal.valueOf(200)));
		System.out.println(ser.createAccount("adit","986632400", BigDecimal.valueOf(200)));
		System.out.println(ser.depositAmount("986632400", BigDecimal.valueOf(200)));
		System.out.println(ser.fundTransfer("986632400","986632499",BigDecimal.valueOf(200)));
		System.out.println(ser.withdrawAmount("986632499",BigDecimal.valueOf(600)));
		System.out.println(ser.showBalance("986632400"));
	
	
		
	}

}
