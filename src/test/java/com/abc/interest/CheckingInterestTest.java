package com.abc.interest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.AccountInterface;
import com.abc.account.CheckingAccount;
import com.abc.common.DateProvider;
import com.abc.interest.CheckingInterest;
import com.abc.interest.InterestInterface;
import com.abc.transaction.Deposit;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;

public class CheckingInterestTest {

	private InterestInterface checkingInterest;
	
	private AccountInterface checkingAccount;
	
	@Before
	public void setUp() throws ParseException{
		this.checkingInterest = new CheckingInterest(0.1);
		final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		this.checkingAccount = new CheckingAccount("C10001"); 
		final Deposit deposit = new Deposit(100000.00);
		checkingAccount.depositMoney(deposit);
		Deencapsulation.setField(checkingAccount, "openDate", format.parse("02/02/2015"));
	}
	
	@Test
	public void testCalculateInterest(){
		
		new MockUp<DateProvider>() {
			@Mock(invocations = 1)
			public int daysDifference(final Date startingDate, final Date endingDate) {
				assertNotNull(startingDate);
				assertNotNull(endingDate);
				return 100;
			}
		};
		
		final double interest = checkingInterest.calculateInterest(checkingAccount);
		assertEquals(27.4, interest, 0);
	}
}

