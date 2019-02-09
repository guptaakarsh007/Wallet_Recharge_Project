package com.capgemini.service;

import java.math.BigDecimal;

import com.capgemini.beans.Customer;

import com.capgemini.exception.AmountNotSufficientException;
import com.capgemini.exception.InvalidMobileNoException;
import com.capgemini.exception.MobileNoExistsException;
import com.capgemini.exception.MobileNoNotNullException;
import com.capgemini.exception.MobileNoNotValidException;
import com.capgemini.exception.NameNotNullException;

public interface WalletService {
	public Customer createAccount(String name,String mobileno,BigDecimal amount) throws NameNotNullException, MobileNoNotNullException, MobileNoExistsException, MobileNoNotValidException;
	public Customer showBalance(String mobileno) throws InvalidMobileNoException;
	public Customer fundTransfer(String sourcemobileno,String targetmobileno,BigDecimal amount) throws InvalidMobileNoException;
	public Customer depositAmount(String mobileno,BigDecimal amount) throws InvalidMobileNoException;
	public Customer withdrawAmount(String mobileno,BigDecimal amount) throws InvalidMobileNoException, AmountNotSufficientException;

}
