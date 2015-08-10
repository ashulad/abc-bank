package com.abc.common;

import com.abc.account.AccountInterface;
import com.abc.transaction.TransactionInterface;


public interface ValidatorInterface {
	
	public void validateTransaction(final AccountInterface account, final TransactionInterface transaction) throws BankException;
}

