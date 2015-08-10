package com.abc.common;

public class TransferFailedException extends BankException{

	private static final long serialVersionUID = 1L;
	
	public TransferFailedException(final String message){
		super(message);
	}

}

