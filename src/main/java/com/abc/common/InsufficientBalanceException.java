package com.abc.common;

public class InsufficientBalanceException extends BankException{

	private static final long serialVersionUID = 1L;
	
	public InsufficientBalanceException(final double availableBalance, final double moneyRequested){
		super(getMessage(availableBalance, moneyRequested));
	}
	
	private static String getMessage(final double balance, final double withdrawn){
		final StringBuilder strBuild = new StringBuilder();
		strBuild.append("Insufficient Balance! " );
		strBuild.append("Current balance in account: " + balance );
		return strBuild.toString();
	}

}