package com.cg.banking.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.banking.dao.BankingDaoImpl;

public class BankingTest {
	static BankingDaoImpl BankingDao = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		BankingDao = new BankingDaoImpl();
		System.out.println("...Start Class...");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("...End Class...");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("...Test Function Start...");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("...Test Function End...");
	}

	@Test
	public void testSignIn() {
		Assert.assertNotNull(BankingDao.signIn("162221", "ay123"));
	}

	@Test
	public void testCreateAcc() {
		Assert.assertNotNull(BankingDao.createAccount("ay123", "Ayushmaan",
				"Current"));
	}

	@Test
	public void testFundTransfer() {
		Assert.assertNotNull(BankingDao.transfer(2000,
				BankingDao.signIn("162221", "ay123"), "160608"));
	}
}
