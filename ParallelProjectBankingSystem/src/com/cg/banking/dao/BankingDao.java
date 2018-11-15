package com.cg.banking.dao;

import com.cg.banking.dto.Customer;

public interface BankingDao {
	public Customer signIn(String cstId, String cstPass);

	public void deposit(int depositAmount, Customer customer);

	public void withdraw(int withdrawAmount, Customer customer);

	public Customer transfer(int transferAmount, Customer customer, String reciever);

	public String createAccount(String password, String custName,
			String accType);
}
