package com.abc.account;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.abc.common.BankException;
import com.abc.common.InsufficientBalanceException;
import com.abc.transaction.Deposit;
import com.abc.transaction.TransactionInterface;
import com.abc.transaction.Withdraw;

public class MaxiSavingsAccountTest {

	private AccountInterface maxSavingAccount;
	
	@Before
	public void setUp(){
		this.maxSavingAccount = new MaxiSavingsAccount("M30001");
	}
	
	@Test
	public void testGetAccountNumber(){
		assertEquals("M30001", maxSavingAccount.getAccountNumber());
	}
	
	@Test
	public void testInitializeTheAccountWithZeroBalance(){
		assertEquals(0.0, maxSavingAccount.getBalance(), 0);
	}

	@Test
	public void testDeposit100(){
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.depositMoney(deposit);
		assertEquals(100.00, maxSavingAccount.getBalance(), 0);
	}
	
	@Test
	public void testWitdraw50() throws BankException{
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.depositMoney(deposit);
		assertEquals(100.00, maxSavingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(50);
		maxSavingAccount.withdrawMoney(withdraw);
		assertEquals(50.00, maxSavingAccount.getBalance(), 0);
	}
	
	
	@Test(expected = InsufficientBalanceException.class)
	public void testGetNotEnoughBalanceException() throws BankException{
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.depositMoney(deposit);
		assertEquals(100.00, maxSavingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(101);
		maxSavingAccount.withdrawMoney(withdraw);
	}
	
	@Test
	public void testGetTransactions() throws BankException{
		final Deposit deposit = new Deposit(100);
		maxSavingAccount.depositMoney(deposit);
		List<TransactionInterface> transactions = maxSavingAccount.getTransactions();
		assertEquals(1, transactions.size());
		final Withdraw withdraw = new Withdraw(50);
		maxSavingAccount.withdrawMoney(withdraw);
		transactions = maxSavingAccount.getTransactions();
		assertEquals(2, transactions.size());
	}
}

