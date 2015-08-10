package com.abc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.abc.customer.CustomerInterface;
import com.abc.customer.Customer;

import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;


public class BankTest {
	
	private Bank bank;
	
	@Injectable
	private CustomerInterface customer1;
	
	@Injectable
	private CustomerInterface customer2;
	
	@Before
	public void setUp(){
		bank = new Bank();
	}
	
	@Test
	public void testDefaultBank(){
		assertEquals(0, bank.getCustomerCount());
	}
	
	@Test
	public void testAddCustomers(){
		CustomerInterface customer1 = new Customer("C10001");
		CustomerInterface customer2 = new Customer("C10002");
		bank.addCustomer(customer1);
		bank.addCustomer(customer2);
		assertEquals(2, bank.getCustomerCount());
	}
	
	@Test
	public void testGetStatement(){
		new NonStrictExpectations() {{
			customer1.getStatement();
			returns("ID=C10001 : Name=lastName, firstName - Number of Account(s): 2");
			customer2.getStatement();
			returns("ID=C10002 : Name=Mathew, George - Number of Account(s): 1");
		}};
		
		bank.addCustomer(customer1);
		bank.addCustomer(customer2);
		final String customerSummary = bank.getCustomerSummary();
		assertEquals("ID=C10001 : Name=lastName, firstName - Number of Account(s): 2\n"
				+ "ID=C10002 : Name=Mathew, George - Number of Account(s): 1\n", customerSummary);
		
		new Verifications() {{
			customer1.getStatement();
			customer2.getStatement();
		}};
	}
	
	@Test
	public void testGetTotalInterestPaid(){
		new NonStrictExpectations() {{
			customer1.getTotalInterestEarned();
			returns(100.00);
			customer2.getTotalInterestEarned();
			returns(100.00);
		}};
		
		bank.addCustomer(customer1);
		bank.addCustomer(customer2);
		assertEquals(200.00, bank.getTotalInterestPaid(), 0);
		
		new Verifications() {{
			customer1.getTotalInterestEarned();
			customer2.getTotalInterestEarned();
		}};
	}
	
}
