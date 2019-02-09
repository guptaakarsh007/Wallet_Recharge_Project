package com.capgemini.juint;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;


import com.capgemini.exception.AmountNotSufficientException;
import com.capgemini.exception.InvalidMobileNoException;
import com.capgemini.exception.MobileNoExistsException;
import com.capgemini.exception.MobileNoNotNullException;
import com.capgemini.exception.MobileNoNotValidException;
import com.capgemini.exception.NameNotNullException;
import com.capgemini.repo.WalletRepo;
import com.capgemini.repo.WalletRepoImpl;
import com.capgemini.service.WalletService;
import com.capgemini.service.WalletServiceImpl;

public class JunitTest {

	WalletRepo repo=new WalletRepoImpl();
	WalletService s=new WalletServiceImpl(repo);

	@Test(expected = com.capgemini.exception.MobileNoNotNullException.class)
	public void whenMobileNoNotGivenThenThrow() throws  NameNotNullException, MobileNoNotNullException,MobileNoExistsException,MobileNoNotValidException
	{
	s.createAccount("akarsh","",BigDecimal.valueOf(200));
	}
	
	
	
	
	@Test(expected = com.capgemini.exception.MobileNoNotValidException.class)
	public void whenMobileNoNotValidThenThrow() throws  NameNotNullException, MobileNoNotNullException,MobileNoExistsException,MobileNoNotValidException
	{
		s.createAccount("eee", "98452763107788",BigDecimal.valueOf(100));
	}
	
	
	@Test(expected = com.capgemini.exception.NameNotNullException.class)
	public void whenNameNotGivenThenThrow() throws  NameNotNullException, MobileNoNotNullException,MobileNoExistsException,MobileNoNotValidException
	{
		s.createAccount("", "899959599",BigDecimal.valueOf(100));
	}
	
	
	
	@Test(expected = com.capgemini.exception.InvalidMobileNoException.class)
	public void whenMobileNoIsInvalidForShowBalanceThenThrow() throws  InvalidMobileNoException
	{
		s.showBalance("899959599");
	}
	
	@Test(expected = com.capgemini.exception.InvalidMobileNoException.class)
	public void whenMobileNoIsInvalidForDepostThenThrow() throws  InvalidMobileNoException
	{
		s.depositAmount("899959599",BigDecimal.valueOf(100));
	}
	@Test(expected = com.capgemini.exception.AmountNotSufficientException.class)
	public void whenAmountNotSufficientForwithdrawThenThrow() throws  InvalidMobileNoException, AmountNotSufficientException, NameNotNullException, MobileNoNotNullException, MobileNoExistsException,MobileNoNotValidException
	{
		s.createAccount("S", "899959599",BigDecimal.valueOf(100));

		s.withdrawAmount("899959599",BigDecimal.valueOf(10000));
	}
	
	
}
