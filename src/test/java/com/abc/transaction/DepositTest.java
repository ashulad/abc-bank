package com.abc.transaction;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.abc.common.DateProvider;


public class DepositTest {
    
	private Deposit deposit;
	
	@Before
	public void setUp(){
		deposit = new Deposit(10.99);
	}
	
	@Test
	public void testGetDepositedAmount(){
		assertEquals(10.99, deposit.getAmount(), 0.0);
	}
	
	@Test
	public void testGetDepositedDate(){
		final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		assertEquals(dateFormat.format(DateProvider.getInstance().now()), dateFormat.format(deposit.getDate()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIllegalArgumentException(){
		new Deposit(-1);
	}
}

