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


public class CheckingAccountTest {
	
	private AccountInterface checkingAccount;
	
	@Before
	public void setUp(){
		checkingAccount = new CheckingAccount("10001");
	}
	
	@Test
	public void testGetAccountNumber(){
		assertEquals("10001", checkingAccount.getAccountNumber());
	}
	
	@Test
	public void testDefaultAccount(){
		assertEquals(0.0, checkingAccount.getBalance(), 0);
	}

	@Test
	public void testDeposit(){
		final Deposit deposit = new Deposit(100);
		checkingAccount.depositMoney(deposit);
		assertEquals(100.00, checkingAccount.getBalance(), 0);
	}
	
	@Test
	public void testWitdraw() throws BankException{
		final Deposit deposit = new Deposit(100);
		checkingAccount.depositMoney(deposit);
		assertEquals(100.00, checkingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(50);
		checkingAccount.withdrawMoney(withdraw);
		assertEquals(50.00, checkingAccount.getBalance(), 0);
	}
	
	
	@Test
	public void testGetTransactions() throws BankException{
		final Deposit deposit = new Deposit(100);
		checkingAccount.depositMoney(deposit);
		List<TransactionInterface> transactions = checkingAccount.getTransactions();
		assertEquals(1, transactions.size());
		final Withdraw withdraw = new Withdraw(50);
		checkingAccount.withdrawMoney(withdraw);
		transactions = checkingAccount.getTransactions();
		assertEquals(2, transactions.size());
	}

	
	@Test(expected = InsufficientBalanceException.class)
	public void testInsufficientBalance() throws BankException{
		final Deposit deposit = new Deposit(100);
		checkingAccount.depositMoney(deposit);
		assertEquals(100.00, checkingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(101);
		checkingAccount.withdrawMoney(withdraw);
	}

}

