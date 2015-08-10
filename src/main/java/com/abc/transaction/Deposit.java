package com.abc.transaction;

import com.abc.common.DateProvider;



public class Deposit extends Transaction implements TransactionInterface{

	private double amount;
	
	public Deposit(final double amount){
		super.validate(amount);
		this.amount = amount;
		super.date = DateProvider.getInstance().now();
	}

	public double getAmount() {
		return amount;
	}

}

