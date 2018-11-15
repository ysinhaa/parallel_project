package com.cg.banking.dto;

import java.time.LocalDate;

public class Customer implements Comparable<Customer>  {
	private String accNumber;
	private String accHolderName;
	private LocalDate custDOJ;
	private String accType;
	private float balance;
	private Double withDrawLimit;

	public Customer(String accNumber, String accHolderName, LocalDate custDOJ,
			String accType, float balance, Double withDrawLimit) {
		super();
		this.accNumber = accNumber;
		this.accHolderName = accHolderName;
		this.custDOJ = custDOJ;
		this.accType = accType;
		this.balance = balance;
		this.withDrawLimit = withDrawLimit;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public LocalDate getCustDOJ() {
		return custDOJ;
	}

	public void setCustDOJ(LocalDate custDOJ) {
		this.custDOJ = custDOJ;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public Double getWithDrawLimit() {
		return withDrawLimit;
	}

	public void setWithDrawLimit(Double withDrawLimit) {
		this.withDrawLimit = withDrawLimit;
	}

	@Override
	public String toString() {
		return "Customer [accNumber=" + accNumber + ", accHolderName="
				+ accHolderName + ", custDOJ=" + custDOJ + ", accType="
				+ accType + ", balance=" + balance + ", withDrawLimit="
				+ withDrawLimit + "]";
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(accNumber);
	}

	@Override
	public boolean equals(Object obj) {
		Customer cust = (Customer) obj;
		if (cust.accNumber.equals(this.accNumber)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Customer cust) {
		if (Integer.parseInt(cust.accNumber) < Integer.parseInt(this.accNumber)) {
			return -1;
		} else if (cust.accNumber.equals(this.accNumber)) {
			return 0;
		} else {
			return +1;
		}
	}
}
