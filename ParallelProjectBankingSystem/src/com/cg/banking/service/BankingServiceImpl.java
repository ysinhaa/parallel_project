package com.cg.banking.service;

import java.util.regex.Pattern;

import com.cg.banking.dao.BankingDao;
import com.cg.banking.dao.BankingDaoImpl;
import com.cg.banking.dto.Customer;
import com.cg.banking.exception.BankingException;

public class BankingServiceImpl implements BankingService {
	BankingDao bankDao = null;

	public BankingServiceImpl() {
	
		bankDao = new BankingDaoImpl();
	}

	@Override
	public Customer signIn(String cstId, String cstPass) {
	
		return bankDao.signIn(cstId, cstPass);
	}

	@Override
	public void deposit(int depositAmount, Customer customer) {
	
		bankDao.deposit(depositAmount, customer);

	}

	@Override
	public void withdraw(int withdrawAmount, Customer customer) {

		bankDao.withdraw(withdrawAmount, customer);

	}

	@Override
	public void transfer(int transferAmount, Customer customer, String reciever) {
	
		Customer transReciever = bankDao.transfer(transferAmount, customer,
				reciever);
		bankDao.withdraw(transferAmount, customer);
		bankDao.deposit(transferAmount, transReciever);

	}

	@Override
	public boolean validateWithdraw(String withdrawAmount, Customer customer)
			throws BankingException {
	
		String amountPattern = "[0-9]*";
		if (Pattern.matches(amountPattern, withdrawAmount)) {
			if (Integer.parseInt(withdrawAmount) <= customer.getBalance()) {
				return true;
			} else {
				throw new BankingException("Insuficient Balance");
			}
		} else {
			throw new BankingException("Amount must be in number format");
		}
	}

	@Override
	public boolean validateEnteredAmount(String amount)
			throws BankingException {
		String amountPattern = "[0-9]*";
		if (Pattern.matches(amountPattern, amount)) {
			return true;
		} else {
			throw new BankingException("Amount must be in number format");
		}
	}

	@Override
	public boolean validateNumber(String choose) throws BankingException {
		
		String chooseNum = "[0-9]*";
		if (Pattern.matches(chooseNum, choose)) {
			return true;
		} else {
			throw new BankingException(
					"Entered choice must be in number format");
		}
	}

	@Override
	public boolean validateName(String name) throws BankingException {
		String namePatter = "[A-Z][a-z]+" + " " + "[A-Z][a-z]+";

		if (Pattern.matches(namePatter, name)) {
			return true;
		} else {
			throw new BankingException(" Invalid input "
					+ " Only Char are  allowed  and should start"
					+ " with Capital ex.    Aakarsh Bhatnagar");
		}
	}

	@Override
	public String createAccount(String password, String custName, String accType) {
		
		return bankDao.createAccount(password, custName, accType);
	}
}

