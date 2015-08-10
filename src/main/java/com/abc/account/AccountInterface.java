package com.abc.account;

import java.util.Date;
import java.util.List;

import com.abc.common.BankException;
import com.abc.transaction.Deposit;
import com.abc.transaction.TransactionInterface;
import com.abc.transaction.Withdraw;


public interface AccountInterface {

	public String getAccountNumber();
	public double getBalance();
	public Date getOpenDate();
	public double getInterestEarned();
	public List<TransactionInterface> getTransactions();

	public void depositMoney(final Deposit deposit);
	public void withdrawMoney(final Withdraw withdraw) throws BankException;
	public void transferMoney(final AccountInterface targetAccount, final double amount) throws BankException;
	
	

	
}