package com.cg.banking.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Random;

import com.cg.banking.dto.Customer;

public class DButil {
	static Random rand = new Random();
	public static HashMap<String, Customer> bankDBMap = new HashMap<>();
	public static HashMap<String, String> bankCredentialsDBMap = new HashMap<>();
	static {
		bankCredentialsDBMap.put("16001", "aa123");
		bankDBMap.put(
				"162221",
				new Customer("162202", "Yashi Sinha", LocalDate.of(1996,
						Month.MAY, 24), "Saving", 15500.0F, 200000.0));
		bankCredentialsDBMap.put("160662", "di123");
		bankDBMap.put("160662", new Customer("160662", "Tanushree Rao",
				LocalDate.of(2018, Month.SEPTEMBER, 26), "Current", 15500.0F,
				200000.0));
		bankCredentialsDBMap.put("160601", "ab123");
		bankDBMap.put("160601", new Customer("160601", "Sanjana Dasari",
				LocalDate.of(2018, Month.SEPTEMBER, 14), "Saving", 15500.0F,
				200000.0));
		bankCredentialsDBMap.put("160608", "ra123");
		bankDBMap.put(
				"160608",
				new Customer("160608", "Aakarsh Bhatnagar", LocalDate.of(2017,
						Month.DECEMBER, 15), "Current", 15500.0F, 200000.0));
	}

	public static String newCustomer(String password, Customer customer) {
		bankCredentialsDBMap.put(customer.getAccNumber(), password);
		bankDBMap.put(customer.getAccNumber(), customer);
		return customer.getAccNumber();
	}

	public static HashMap<String, String> getAllCustCred() {
		return bankCredentialsDBMap;
	}

	public static HashMap<String, Customer> getAllCustomer() {
		return bankDBMap;
	}
}
