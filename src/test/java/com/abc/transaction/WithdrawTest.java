package com.abc.transaction;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.abc.common.DateProvider;

public class WithdrawTest {
	
	private Withdraw withdraw;
	
	@Before
	public void setUp(){
		this.withdraw = new Withdraw(9.9);
	}

	@Test
	public void testGetWithdrawAmount(){
		assertEquals(9.9, withdraw.getAmount(), 0.0);
	}
	
	@Test
	public void testGetDepositedDate(){
		final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		assertEquals(dateFormat.format(DateProvider.getInstance().now()), dateFormat.format(withdraw.getDate()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetIllegalArgumentException(){
		new Withdraw(-1);
	}
}

