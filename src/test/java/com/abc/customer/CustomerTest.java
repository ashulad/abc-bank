package com.abc.customer;

import static org.junit.Assert.assertEquals;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.AccountInterface;
import com.abc.account.SavingsAccount;

public class CustomerTest {
	
	private CustomerInterface customer;
	
	@Injectable
	private AccountInterface savingsAccount;
	
	@Injectable
	private AccountInterface checkingAccount;

	@Before
	public void setUp(){
		this.customer = new Customer("C10001");
		((Customer) customer).setIndividualInformation(new CustomerInfo("Ashok", "Lad"));
	}

	@Test
	public void testGetCustomerId(){
		assertEquals("C10001", customer.getId());
	}
	
	@Test
	public void testInitializeAccounts(){
		assertEquals(0, customer.getNumberOfAccounts());
		assertEquals(0, customer.getTotalInterestEarned(), 0);
	}
	
	@Test
	public void testSetIndividualInformation(){
		assertEquals("Lad, Ashok", customer.getDisplayName()); 
	}
	
	@Test
	public void testOpenAccount(){
		final AccountInterface savingsAccount = new SavingsAccount("S20001");
		customer.openAccount(savingsAccount);
		assertEquals(1, customer.getNumberOfAccounts());
	}
	
	@Test
	public void testGetTotalInterestEarned(){
		new NonStrictExpectations(){{
			savingsAccount.getInterestEarned();
			returns(100.00);
			checkingAccount.getInterestEarned();
			returns(10.00);
		}};
		
		customer.openAccount(savingsAccount);
		customer.openAccount(checkingAccount);
		assertEquals(110.00, customer.getTotalInterestEarned(), 0);
		
		new Verifications() {{
			savingsAccount.getInterestEarned();
			savingsAccount.getInterestEarned();
		}};
	}
	
	@Test
	public void testGetCustomerStatement(){
		customer.openAccount(savingsAccount);
		customer.openAccount(checkingAccount);
		assertEquals("Customer ID: C10001 - Customer Name: Lad, Ashok - Number of Account(s): 2", customer.getStatement());
	}
}

