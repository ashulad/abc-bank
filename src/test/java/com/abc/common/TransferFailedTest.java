package com.abc.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.abc.common.BankException;
import com.abc.common.TransferFailedException;

public class TransferFailedTest {
	
	private BankException transferFailedException;

	@Before
	public void setUp(){
		transferFailedException = new TransferFailedException("Failed due to unexpected error!, please contact customer support");
	}
	
	@Test
	public void testGetMessage(){
		assertEquals("Failed due to unexpected error!, please contact customer support", transferFailedException.getMessage());
	}
}

