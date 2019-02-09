package com.capgemini.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.beans.Customer;
import com.capgemini.beans.Wallet;

import com.capgemini.exception.AmountNotSufficientException;
import com.capgemini.exception.InvalidMobileNoException;
import com.capgemini.exception.MobileNoExistsException;
import com.capgemini.exception.MobileNoNotNullException;
import com.capgemini.exception.MobileNoNotValidException;
import com.capgemini.exception.NameNotNullException;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService {
	WalletRepo repo;

	public WalletServiceImpl(WalletRepo repo) {
		this.repo = repo;
	}

	public Customer createAccount(String name, String mobileno, BigDecimal amount)
			throws NameNotNullException, MobileNoNotNullException, MobileNoExistsException,MobileNoNotValidException
	{
		Customer cust = new Customer();
		Wallet wallet = new Wallet();
		if (name == "")
			throw new NameNotNullException();
		if (mobileno == "")
			throw new MobileNoNotNullException();
		if(validatemobNo(mobileno)!=true)
			throw new MobileNoNotValidException();
		/*if(amount.doubleValue()>0)
			throw new AmountNotNullException();*/
		if (search(mobileno) != null)
			throw new MobileNoExistsException();
		

		wallet.setBalance(amount);
		cust.setName(name);
		cust.setMobileno(mobileno);
		cust.setWallet(wallet);
		if (repo.save(cust))
			return cust;

		return null;
	}

	@Override
	public Customer showBalance(String mobileno) throws InvalidMobileNoException {
		Customer cust = repo.findOne(mobileno);
		if (cust != null) {
			System.out.println("your balance is--");
			return repo.findOne(mobileno);
		}
		throw new InvalidMobileNoException();
	}

	@Override
	public Customer fundTransfer(String sourcemobileno, String targetmobileno, BigDecimal amount)
			throws InvalidMobileNoException {
		Customer cust = repo.findOne(sourcemobileno);
		Customer cust1 = repo.findOne(targetmobileno);
		if (cust != null && cust1 != null) {
			Wallet wallet1 = new Wallet();
			Wallet wallet2 = new Wallet();
			wallet1.setBalance(amount.add(cust.getWallet().getBalance()));
			wallet2.setBalance(cust.getWallet().getBalance().subtract(amount));
			cust1.setWallet(wallet1);
			cust.setWallet(wallet2);
			System.out.println("fund transfer success");

			System.out.println(cust);
			System.out.println("updated balance");
			return cust1;

		}
		throw new InvalidMobileNoException();
	}

	@Override
	public Customer depositAmount(String mobileno, BigDecimal amount) throws InvalidMobileNoException {
		Customer cust = repo.findOne(mobileno);

		if (cust != null) {
			Wallet wallet = new Wallet();
			wallet.setBalance(amount.add(cust.getWallet().getBalance()));
			cust.setWallet(wallet);

			System.out.println("deposit successfull");
			return cust;
		}
		throw new InvalidMobileNoException();

	}

	@Override
	public Customer withdrawAmount(String mobileno, BigDecimal amount)
			throws InvalidMobileNoException, AmountNotSufficientException {
		Customer cust = repo.findOne(mobileno);
		if (cust == null)
			throw new InvalidMobileNoException();
		if (amount.compareTo(cust.getWallet().getBalance()) > 0) {
			throw new AmountNotSufficientException();
		}
		if (cust != null) {
			Wallet wallet = new Wallet();

			wallet.setBalance(amount.subtract(cust.getWallet().getBalance()));

			cust.setWallet(wallet);
			repo.save(cust);
			System.out.println("withdraw successfull");
			return cust;
		}
		return null;
	}

	public Customer search(String mobileno) {
		return repo.findOne(mobileno);
	}
	
	public boolean validatemobNo(String mobNo)  {
		
		Pattern pat=Pattern.compile("[6-9]{1}[0-9]{2,9}");
		Matcher mat=pat.matcher(mobNo);
		return mat.matches();
	
	}

}
