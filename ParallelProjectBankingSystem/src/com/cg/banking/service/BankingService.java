package com.cg.banking.service;

import com.cg.banking.dto.Customer;
import com.cg.banking.exception.BankingException;

public interface BankingService {
	public Customer signIn(String cstId, String cstPass);

	public void deposit(int depositAmount, Customer customer);

	public void withdraw(int withdrawAmount, Customer customer);

	public void transfer(int transferAmount, Customer customer, String reciever);

	public boolean validateWithdraw(String withdrawAmount, Customer customer)
			throws BankingException;

	public boolean validateEnteredAmount(String withdrawAmount)
			throws BankingException;

	public boolean validateNumber(String choose) throws BankingException;

	public boolean validateName(String name) throws BankingException;

	public String createAccount(String password, String custName, String accType);

}
