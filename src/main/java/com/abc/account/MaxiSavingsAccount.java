package com.abc.account;

import java.util.ArrayList;

import com.abc.common.DateProvider;
import com.abc.interest.MaxiSavingsInterest;
import com.abc.transaction.TransactionInterface;



public class MaxiSavingsAccount extends Account implements AccountInterface{

	public MaxiSavingsAccount(final String number){
		super(number);
		super.interest = new MaxiSavingsInterest(0.1, 5);
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

