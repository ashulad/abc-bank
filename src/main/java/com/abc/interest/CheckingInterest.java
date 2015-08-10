package com.abc.interest;

import com.abc.account.AccountInterface;
import com.abc.common.DateProvider;


public class CheckingInterest extends Interest implements InterestInterface{
	
	private double rate;
	
	public CheckingInterest(final double rate){
		this.rate = rate;
	}

	public double calculateInterest(AccountInterface account) {
		return super.calculateInterest(account.getBalance(), rate, account.getOpenDate(), DateProvider.getInstance().now());
	}
	
}
	
