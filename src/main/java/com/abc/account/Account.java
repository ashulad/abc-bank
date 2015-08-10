package com.abc.account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.abc.common.BankException;
import com.abc.common.TransferFailedException;
import com.abc.common.ValidatorInterface;
import com.abc.common.WithdrawValidator;
import com.abc.interest.InterestInterface;
import com.abc.transaction.Deposit;
import com.abc.transaction.TransactionInterface;
import com.abc.transaction.Withdraw;


public abstract class Account implements AccountInterface{
	
	protected List<ValidatorInterface> validatorList;
	
	protected List<TransactionInterface> transactionList;
	
	protected InterestInterface interest;
	
	private double balance;
	
	protected Date openDate;
	
	private String accountNumber;
	
	protected Account(final String number){
		this.accountNumber = number;
	}

	public synchronized void depositMoney(final Deposit deposit) {
		this.balance += deposit.getAmount();
		transactionList.add(deposit);
	}

	public synchronized void withdrawMoney(final Withdraw withdraw) throws BankException{
		for(final ValidatorInterface transactionValidator : validatorList){
			transactionValidator.validateTransaction(this, withdraw);
		}
		this.balance -= withdraw.getAmount();
		transactionList.add(withdraw);
	}

	public List<TransactionInterface> getTransactions() {
		return new ArrayList<TransactionInterface>(transactionList);
	}
	
	public double getBalance(){
		return balance;
	}
	
	public Date getOpenDate(){
		return this.openDate;
	}
	
	public String getAccountNumber(){
		return this.accountNumber;
	}
	
	public void transferMoney(final AccountInterface targetAccount, final double amount) throws BankException{
		final Withdraw withdraw = new Withdraw(amount);
		this.withdrawMoney(withdraw);
		final Deposit deposit = new Deposit(amount);
		try {
			targetAccount.depositMoney(deposit);
		} catch (final Exception e) {
			this.depositMoney(deposit);
			throw new TransferFailedException("Transfer failed due to unexpected reasons, please contact the customer support.");
		}
	}
	
	 //Interest earned so far.
	public double getInterestEarned(){
		double interestEarned = 0;
		if(null != interest){
			interestEarned = interest.calculateInterest(this);
		}
		return interestEarned;
	}


	protected List<ValidatorInterface> getTransactionValidators(){
		final List<ValidatorInterface> transactinValidators = new ArrayList<ValidatorInterface>();
		transactinValidators.add(new WithdrawValidator());
		return transactinValidators;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		return true;
	}
	
}

