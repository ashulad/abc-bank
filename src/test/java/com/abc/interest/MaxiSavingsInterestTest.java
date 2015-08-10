package com.abc.interest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.AccountInterface;
import com.abc.account.MaxiSavingsAccount;
import com.abc.common.BankException;
import com.abc.common.DateProvider;
import com.abc.interest.InterestInterface;
import com.abc.interest.MaxiSavingsInterest;
import com.abc.transaction.Deposit;
import com.abc.transaction.Withdraw;

public class MaxiSavingsInterestTest {

	private InterestInterface maxiSavingsInterest;
	
	private AccountInterface maxSavingAccount;
	
	@Before
	public void setUp() throws BankException, ParseException{
		maxiSavingsInterest = new MaxiSavingsInterest(0.1, 5);
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		maxSavingAccount = new MaxiSavingsAccount("M30001");
		Deencapsulation.setField(maxSavingAccount, "openDate", format.parse("01/01/2014"));
		final Deposit deposit = new Deposit(10000.00);
		maxSavingAccount.depositMoney(deposit);
		final Withdraw withdraw1 = new Withdraw(10.00);
		maxSavingAccount.withdrawMoney(withdraw1);
		final Withdraw withdraw2 = new Withdraw(11.00);
		maxSavingAccount.withdrawMoney(withdraw2);
	}
	
	@Test
	public void testNotBeEligibleForMaxSavingsRate(){
		final boolean isEligibleFroMaxSabings = Deencapsulation.invoke(maxiSavingsInterest, 
				"isMaxiSavingsRate", new Object [] {maxSavingAccount});
		assertFalse(isEligibleFroMaxSabings);
	}
	
	@Test 
	public void testBeEligibleForMaxSavingsRate() throws BankException, ParseException{
		maxSavingAccount = new MaxiSavingsAccount("M30001");
		final Deposit deposit = new Deposit(100.00);
		maxSavingAccount.depositMoney(deposit);
		final Withdraw withdraw3 = new Withdraw(11.00);
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Deencapsulation.setField(withdraw3, "date", format.parse("09/17/2014"));
		maxSavingAccount.withdrawMoney(withdraw3);
		final boolean isEligibleFroMaxSabings = Deencapsulation.invoke(maxiSavingsInterest, 
				"isMaxiSavingsRate", new Object [] {maxSavingAccount});
		assertTrue(isEligibleFroMaxSabings);
	}
	
	@Test
	public void testCalculateInterestWithDefaultRate(){
		
		new MockUp<DateProvider>() {
			@Mock(invocations = 2)
			public int daysDifference(final Date startingDate, final Date endingDate) {
				assertNotNull(startingDate);
				assertNotNull(endingDate);
				return 9;
			}
		};
		
		final double interest = maxiSavingsInterest.calculateInterest(maxSavingAccount);
		assertEquals(.25, interest, 0);
	}
	
	@Test
	public void testCalculateInterestWithMaxSavingsRate() throws ParseException{
		
		new MockUp<DateProvider>() {
			@Mock(invocations = 1)
			public int daysDifference(final Date startingDate, final Date endingDate) {
				assertNotNull(startingDate);
				assertNotNull(endingDate);
				return 150;
			}
		};
		
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		maxSavingAccount = new MaxiSavingsAccount("M30001");
		final Deposit deposit = new Deposit(2200.00);
		maxSavingAccount.depositMoney(deposit);
		Deencapsulation.setField(maxSavingAccount, "openDate", format.parse("02/02/2015"));
		final double interest = maxiSavingsInterest.calculateInterest(maxSavingAccount);
		assertEquals(45.21, interest, 0);
	}
	
}

