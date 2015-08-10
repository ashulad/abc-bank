package com.abc.interest;

import com.abc.account.AccountInterface;
import com.abc.common.DateProvider;


public class SavingsInterest extends Interest implements InterestInterface{
	//Rate for first 1000
	private double firstRate;
	//Rate for above 1000
	private double secondRate;
	
	public SavingsInterest(final double firstRate, final double secondRate){
		this.firstRate = firstRate;
		this.secondRate = secondRate;
	}
	
	public double calculateInterest(AccountInterface account) {
		double interest;
		if(account.getBalance() > 1000.00){
			interest = super.calculateInterest((account.getBalance() - 1000), 
					secondRate, account.getOpenDate(), DateProvider.getInstance().now());
			interest += super.calculateInterest(1000, firstRate, account.getOpenDate(), DateProvider.getInstance().now());
		}else{
			interest = super.calculateInterest(account.getBalance(), firstRate, account.getOpenDate(), 
					DateProvider.getInstance().now());
		}
		return interest;
	}
}

