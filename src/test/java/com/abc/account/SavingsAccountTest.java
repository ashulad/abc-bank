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

public class SavingsAccountTest {
	
	private AccountInterface savingAccount;
	
	@Before
	public void setUp(){
		savingAccount = new SavingsAccount("S20001");
	}
	
	@Test
	public void testGetAccountNumber(){
		assertEquals("S20001", savingAccount.getAccountNumber());
	}
	
	@Test
	public void testInitializeTheAccountWithZeroBalance(){
		assertEquals(0.0, savingAccount.getBalance(), 0);
	}
	
	@Test
	public void testDeposit100(){
		final Deposit deposit = new Deposit(100.00);
		savingAccount.depositMoney(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
	}
	
	@Test
	public void shoudlWithDraw50() throws BankException{
		final Deposit deposit = new Deposit(100.00);
		savingAccount.depositMoney(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(50.00);
		savingAccount.withdrawMoney(withdraw);
		assertEquals(50.00, savingAccount.getBalance(), 0);
	}
	
	@Test
	public void shoudlWithDraw100() throws BankException{
		final Deposit deposit = new Deposit(100.00);
		savingAccount.depositMoney(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(100.00);
		savingAccount.withdrawMoney(withdraw);
		assertEquals(0.00, savingAccount.getBalance(), 0);
	}
	
	
	@Test(expected = InsufficientBalanceException.class)
	public void testGetNotEnoughBalanceException() throws BankException{
		final Deposit deposit = new Deposit(100);
		savingAccount.depositMoney(deposit);
		assertEquals(100.00, savingAccount.getBalance(), 0);
		final Withdraw withdraw = new Withdraw(101);
		savingAccount.withdrawMoney(withdraw);
	}
	

	@Test
	public void testGetTransactions() throws BankException{
		final Deposit deposit = new Deposit(100);
		savingAccount.depositMoney(deposit);
		List<TransactionInterface> transactions = savingAccount.getTransactions();
		assertEquals(1, transactions.size());
		final Withdraw withdraw = new Withdraw(50);
		savingAccount.withdrawMoney(withdraw);
		transactions = savingAccount.getTransactions();
		assertEquals(2, transactions.size());
	}

}

