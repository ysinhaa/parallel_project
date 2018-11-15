package com.cg.banking.dao;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import com.cg.banking.dto.Credentials;
import com.cg.banking.dto.Customer;
import com.cg.banking.util.DButil;

public class BankingDaoImpl implements BankingDao{
	static Random rand = new Random();

	@Override
	public Customer signIn(String cstId, String cstPass) {
		
		Credentials credential = null;
		Customer customer = null;
		HashMap<String, String> credDBMap = DButil.getAllCustCred();
		HashMap<String, Customer> bankDBMap = DButil.getAllCustomer();
		Iterator<String> itKey = credDBMap.keySet().iterator();
		Iterator<String> itPass = credDBMap.values().iterator();
		Iterator<Customer> it = bankDBMap.values().iterator();
		while (itKey.hasNext()) {
			credential = new Credentials(itKey.next(), itPass.next());
			customer = it.next();
			if (credential.getAccNumber().equals(cstId)
					&& credential.getPassword().equals(cstPass)) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public void deposit(int depositAmount, Customer customer) {
		
		customer.setBalance(customer.getBalance() + depositAmount);

	}

	@Override
	public void withdraw(int withdrawAmount, Customer customer) {
		
		customer.setBalance(customer.getBalance() - withdrawAmount);

	}

	@Override
	public Customer transfer(int transferAmount, Customer customer,
			String reciever) {
		
		Customer transReciever = null;
		HashMap<String, Customer> bankDBMap = DButil.getAllCustomer();
		Iterator<Customer> it = bankDBMap.values().iterator();
		while (it.hasNext()) {
			transReciever = it.next();
			if (transReciever.getAccNumber().equals(reciever)) {
				return transReciever;
			}
		}
		return transReciever;
	}

	@Override
	public String createAccount(String password, String custName, String accType) {
		
		return DButil.newCustomer(password,
				new Customer(Integer.toString(rand.nextInt(50) + 1), custName,
						LocalDate.now(), accType, 0.0F, 200000.0));
	}
}
