package com.abc.interest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mockit.Deencapsulation;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.AccountInterface;
import com.abc.account.CheckingAccount;
import com.abc.account.MaxiSavingsAccount;
import com.abc.account.SavingsAccount;
import com.abc.interest.InterestInterface;

public class InterestTest {
	
	@Injectable
	private InterestInterface maxiSavingsInterest;
	
	@Injectable
	private InterestInterface savingsInterest;
	
	@Injectable
	private InterestInterface checkingInterest;
	
	private AccountInterface checkingAccount;
	
	private AccountInterface savingsAccount;
	
	private AccountInterface maxSavingsAccount;
	
	@Before
	public void setUp(){
		this.checkingAccount = new CheckingAccount("C10001");
		this.savingsAccount = new SavingsAccount("S20001");
		this.maxSavingsAccount = new MaxiSavingsAccount("M30001");
		Deencapsulation.setField(checkingAccount, "interest", checkingInterest);
		Deencapsulation.setField(savingsAccount, "interest", savingsInterest);
		Deencapsulation.setField(maxSavingsAccount, "interest", maxiSavingsInterest);
	}

	@Test
	public void testInitialCheckingInterest(){
		this.savingsAccount = new CheckingAccount("C10001");
		assertEquals(0.0, savingsAccount.getInterestEarned(), 0);
	}
	
	@Test
	public void testEarnedCheckingIntrest(){
		new NonStrictExpectations() {{
			checkingInterest.calculateInterest((AccountInterface) any);
			returns(1.00);
		}};
		
		assertEquals(1.00, checkingAccount.getInterestEarned(), 0);
		
		new Verifications() {{
			AccountInterface account;
			checkingInterest.calculateInterest(account = withCapture());
			assertTrue(account instanceof CheckingAccount);
			assertEquals(0.0, account.getBalance(), 0);
		}};
	}
	
	@Test
	public void testInitialSavingsInterest(){
		this.savingsAccount = new SavingsAccount("S20001");
		assertEquals(0.0, savingsAccount.getInterestEarned(), 0);
	}
	
	@Test
	public void testEarnedSavingsIntrest(){
		new NonStrictExpectations() {{
			savingsInterest.calculateInterest((AccountInterface) any);
			returns(10.00);
		}};
		
		assertEquals(10.00, savingsAccount.getInterestEarned(), 0);
		
		new Verifications() {{
			AccountInterface account;
			savingsInterest.calculateInterest(account = withCapture());
			assertTrue(account instanceof SavingsAccount);
			assertEquals(0.0, account.getBalance(), 0);
		}};
	}
	
	@Test
	public void testInitialMaxiSavingsInterest(){
		this.savingsAccount = new MaxiSavingsAccount("M30001");
		assertEquals(0.0, savingsAccount.getInterestEarned(), 0);
	}


	@Test
	public void testEarnedMaxiSavingsIntrest(){
		new NonStrictExpectations() {{
			maxiSavingsInterest.calculateInterest((AccountInterface) any);
			returns(111.00);
		}};
		
		assertEquals(111.00, maxSavingsAccount.getInterestEarned(), 0);
		
		new Verifications() {{
			AccountInterface account;
			maxiSavingsInterest.calculateInterest(account = withCapture());
			assertTrue(account instanceof MaxiSavingsAccount);
			assertEquals(0.0, account.getBalance(), 0);
		}};
	}

}

