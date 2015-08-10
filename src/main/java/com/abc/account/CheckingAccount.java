package com.abc.account;

import java.util.ArrayList;

import com.abc.common.DateProvider;
import com.abc.interest.SavingsInterest;
import com.abc.transaction.TransactionInterface;



public class CheckingAccount extends Account implements AccountInterface{

	public CheckingAccount(final String number){
		super(number);
		super.interest = new SavingsInterest(0.1, 0.2);
		super.transactionList = new ArrayList<TransactionInterface>();
		super.validatorList = getTransactionValidators();
		openDate = DateProvider.getInstance().now();
	}
	
	public int hashCode() {
		return super.hashCode();
	}

	public boolean equals(Object obj){
		return super.equals(obj);
	}
}

