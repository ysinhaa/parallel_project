package com.cg.banking.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import com.cg.banking.dto.Customer;
import com.cg.banking.exception.BankingException;
import com.cg.banking.service.BankingService;
import com.cg.banking.service.BankingServiceImpl;

public class Client {
	static Scanner sc = new Scanner(System.in);
	static BankingService bankingSer = null;

	public static void main(String[] args) {
		
		bankingSer = new BankingServiceImpl();
		String choice;
		while (true) {
			System.out.println("\nWelcome to Wallet App for XYZ Bank");
			System.out.println("1. Create New Account");
			System.out.println("2. SignIn");
			choice = sc.next();
			try {
				if (bankingSer.validateNumber(choice)) {
					switch (Integer.parseInt(choice)) {
					case 1:
						createAccount();
						break;
					case 2:
						signIn();
						break;
					default:
						System.out.println("!!!Please Enter a valid choice!!!");
						System.out.println("\n...Thank You...");
					}
				}
			} catch (BankingException e) {
				
				System.out.println(e.getMessage());
				System.out
						.println("...Unsuccessful Transaction...\n...Thank You...");
			}
		}

	}

	private static void signIn() {
	
		System.out.println("Enter customer Id");
		String cstId = sc.next();
		System.out.println("Enter Password");
		String cstPass = sc.next();
		Customer customer = bankingSer.signIn(cstId, cstPass);
		if (customer.getAccHolderName().equals(null)) {
			System.out.println("...Invalid Credentials...");
			signIn();
		} else {
			openAccount(customer);
		}
	}

	private static void openAccount(Customer customer) {
		
		System.out.println("...Hi " + customer.getAccHolderName()
				+ "...\n...Welcome to XYZ Bank...");
		System.out.println("Please enter a valid choice:\n"
				+ "1. Print Mini Statement\n" + "2. Show Balance\n"
				+ "3. Deposit\n" + "4. Withdraw\n" + "5. Fund Transfer");
		String choose = sc.next();
		try {
			if (bankingSer.validateNumber(choose)) {
				switch (Integer.parseInt(choose)) {
				case 1:
					System.out.println("\nAccount Number: "
							+ customer.getAccNumber());
					System.out.println("\nBalance: Rs. "
							+ customer.getBalance());
					System.out.println("\nAccount Type: "
							+ customer.getAccType());
					System.out.println("\nWithdraw Limit: Rs. "
							+ customer.getWithDrawLimit());
					System.out
							.println("\n...Transaction Successful...\n...Thank You...");
					customer = null;
					break;

				case 2:
					System.out.println("... Rs. " + customer.getBalance()
							+ " ...");
					System.out
							.println("...Transaction Successful...\n...Thank You...");
					customer = null;
					break;

				case 3:
					System.out.println("Enter amount Rs.");
					String depositAmount = sc.next();
					try {
						if (bankingSer.validateEnteredAmount(depositAmount)) {
							bankingSer.deposit(Integer.parseInt(depositAmount),
									customer);
							System.out
									.println("...Transaction Successful...\n...Thank You...");
						}
					} catch (BankingException e) {
						
						System.out.println(e.getMessage());
						System.out
								.println("...Unsuccessful Transaction...\n...Thank You...");
					}
					customer = null;
					break;

				case 4:
					System.out.println("Enter amount");
					String withdrawAmount = sc.next();

					try {
						if (bankingSer.validateWithdraw(withdrawAmount, customer)) {
							bankingSer.withdraw(Integer.parseInt(withdrawAmount),
									customer);
							System.out
									.println("...Transaction Successful...\n...Thank You...");
						}
					} catch (BankingException e) {
						
						System.out.println(e.getMessage());
						System.out
								.println("...Unsuccessful Transaction...\n...Thank You...");
					}
					customer = null;
					break;

				case 5:
					System.out
							.println("Enter accountId in which money to be transfered");
					String reciever = sc.next();
					System.out.println("Enter amount");
					String transferAmount = sc.next();

					try {
						if (bankingSer.validateWithdraw(transferAmount, customer)) {
							bankingSer.transfer(Integer.parseInt(transferAmount),
									customer, reciever);
							System.out
									.println("...Transaction Successful...\n...Thank You...");
						}
					} catch (BankingException e) {
						
						System.out.println(e.getMessage());
						System.out
								.println("...Unsuccessful Transaction...\n...Thank You...");
					}
					customer = null;
					break;

				default:
					System.out.println("!!!Please Enter Valid Choice!!!");
					System.out
							.println("...Unsuccessful Transaction...\n...Thank You...");
					customer = null;
					break;
				}
			}
		} catch (BankingException e) {
		
			System.out.println(e.getMessage());
			System.out
					.println("...Unsuccessful Transaction...\n...Thank You...");
		}
	}

	private static void createAccount() {
		
		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				System.in));
		String accType = null;
		String password = null;
		String confirmPassword = null;
		int limit = 3;
		System.out.println("Enter Full Name");
		String custName;
		try {
			custName = buffer.readLine();

			try {
				if (bankingSer.validateName(custName)) {
					System.out
							.println("Select Account Type:\n1. Saving\n2. Current");
					String choice = sc.next();
					try {
						if (bankingSer.validateNumber(choice)) {
							switch (Integer.parseInt(choice)) {
							case 1:
								accType = "Saving";
								break;
							case 2:
								accType = "Current";
								break;
							default:
								System.out
										.println("!!!Please Enter a Valid Choice!!!");
								System.out.println("\n...Thank You...");
							}
							while (limit > 0) {
								System.out.println("Enter password:");
								password = sc.next();
								System.out.println("Confirm password:");
								confirmPassword = sc.next();
								if (password.equals(confirmPassword)) {
									password = confirmPassword;
									break;
								} else {
									password = null;
									limit--;
									System.out
											.println("Password and Confirm password not matched.\nEnter Again");
									if (limit >= 1) {
									} else {
										break;
									}

								}
							}

							if (password == null) {
								System.out
										.println("!!!Number of Attempts exceeded!!!");
							} else {
								String custAccNumber = bankingSer.createAccount(
										password, custName, accType);
								System.out
										.println("...Account Successfully Created...\n"
												+ "Kindly take note of your account Id: "
												+ custAccNumber
												+ "\n...Thank You...");
							}
						}
					} catch (BankingException e) {
						
						System.out.println(e.getMessage());
						System.out.println("\n...Thank You...");
					}
				}
			} catch (BankingException e) {
				
				System.out.println(e.getMessage());
				System.out.println("\n...Thank You...");
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
}

