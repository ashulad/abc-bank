package com.abc.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.abc.common.BankException;
import com.abc.common.InsufficientBalanceException;

public class InsufficientBalanceTest {
	
	private BankException insufficientBalanceException;
	
	@Before
	public void setUp(){
		this.insufficientBalanceException = new InsufficientBalanceException(5000.00, 7000.00);
	}
	
	@Test
	public void testGetNotEnoughBalanceExceptionMessage(){
		assertEquals("Insufficient Balance! Current balance in account: 5000.0", 
				insufficientBalanceException.getMessage());
	}

}

