package com.abc.interest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.AccountInterface;
import com.abc.account.SavingsAccount;
import com.abc.common.DateProvider;
import com.abc.interest.InterestInterface;
import com.abc.interest.SavingsInterest;
import com.abc.transaction.Deposit;

public class SavingsInterestTest {
	
	private InterestInterface savingsInterest;
	
	private AccountInterface savingsAccount;
	
	@Before
	public void setUp() throws ParseException{
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		savingsInterest = new SavingsInterest(0.1, 0.2);
		this.savingsAccount = new SavingsAccount("S20001"); 
		final Deposit deposit = new Deposit(1000.00);
		savingsAccount.depositMoney(deposit);
		Deencapsulation.setField(savingsAccount, "openDate", format.parse("01/01/2014"));
	}
	
	@Test
	public void testCalculateInterestForBalance1000(){
		new MockUp<DateProvider>() {
			@Mock(invocations = 1)
			public int daysDifference(final Date startingDate, final Date endingDate) {
				assertNotNull(startingDate);
				assertNotNull(endingDate);
				return 270;
			}
		};
		
		final double interest = savingsInterest.calculateInterest(savingsAccount);
		assertEquals(.74, interest, 0);
	} 
	
	@Test
	public void testCalculateInterestForBalance10000(){
		new MockUp<DateProvider>() {
			@Mock(invocations = 2)
			public int daysDifference(final Date startingDate, final Date endingDate) {
				assertNotNull(startingDate);
				assertNotNull(endingDate);
				return 270;
			}
		};
		
		final Deposit deposit = new Deposit(9000.00);
		savingsAccount.depositMoney(deposit);
		final double interest = savingsInterest.calculateInterest(savingsAccount);
		assertEquals((13.32 + .74), interest, 0);
	} 

}

