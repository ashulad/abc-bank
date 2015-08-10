package com.abc.interest;

import com.abc.account.AccountInterface;
import com.abc.common.DateProvider;
import com.abc.transaction.TransactionInterface;
import com.abc.transaction.Withdraw;


public class MaxiSavingsInterest extends Interest implements InterestInterface{

	private double firstRate;
	private double secondRate;
	
	public MaxiSavingsInterest(final double firstRate, final double secondRate){
		this.firstRate = firstRate;
		this.secondRate = secondRate;
	}
	
	public double calculateInterest(final AccountInterface account) {
		double interest;
		if(isMaxiSavingsRate(account)){
			interest = super.calculateInterest(account.getBalance(), secondRate, account.getOpenDate(), 
					DateProvider.getInstance().now());
		}else{
			interest = super.calculateInterest(account.getBalance(), firstRate, account.getOpenDate(), 
					DateProvider.getInstance().now());
		}
		return interest;
	}
	

	private boolean isMaxiSavingsRate( AccountInterface account){
		boolean flag = true;
		for(final TransactionInterface transaction : account.getTransactions()){
			if(transaction instanceof Withdraw){
				final int noOfDays = DateProvider.getInstance().daysDifference(transaction.getDate(), DateProvider.getInstance().now());
				if(noOfDays <= 10){
					flag = false;
					break;
				}
			}
		}
		return flag;
	}
	
}

