package com.abc.common;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.abc.account.AccountInterface;
import com.abc.account.SavingsAccount;
import com.abc.common.BankException;
import com.abc.common.InsufficientBalanceException;
import com.abc.common.ValidatorInterface;
import com.abc.common.WithdrawValidator;
import com.abc.transaction.Deposit;
import com.abc.transaction.Withdraw;


public class WithdrawValidatorTest {
	
	private ValidatorInterface withdrawValidator;
	
	@Before
	public void setUp(){
		withdrawValidator = new WithdrawValidator();
	}

	@Test
	public void testValidateSuccessfully() throws BankException{
		final AccountInterface account = new SavingsAccount("S20001");
		final Deposit deposit = new Deposit(100.00);
		account.depositMoney(deposit);
		withdrawValidator.validateTransaction(account, new Withdraw(10.00));
	}
	
	@Test(expected = InsufficientBalanceException.class)
	public void testThrowNotEnoughBalanceException() throws BankException{
		final AccountInterface account = new SavingsAccount("S20001");
		final Deposit deposit = new Deposit(100.00);
		account.depositMoney(deposit);
		withdrawValidator.validateTransaction(account, new Withdraw(101.00));
	}
	
	@Test()
	public void testGetValidExceptionMessage(){
		final AccountInterface account = new SavingsAccount("S20001");
		final Deposit deposit = new Deposit(100.00);
		account.depositMoney(deposit);
		try {
			withdrawValidator.validateTransaction(account, new Withdraw(101.00));
		} catch (final BankException e) {
			final String message = e.getMessage();
			assertEquals("Insufficient Balance! Current balance in account: 100.0", message);
		}
	}
}

	