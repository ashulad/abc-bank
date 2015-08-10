package com.abc.customer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CustomerInfoTest {

	private CustomerInfo customerInfo;
	
	@Before
	public void setUp(){
		customerInfo = new CustomerInfo("Ashok", "Lad");
	}
	
	@Test
	public void testReturnFirstName(){
		assertEquals("Ashok", customerInfo.getFirstName());
	}
	
	@Test
	public void testReturnLastName(){
		assertEquals("Lad", customerInfo.getLastName());
	}
	
}

