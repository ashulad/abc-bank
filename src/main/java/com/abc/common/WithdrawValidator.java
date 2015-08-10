package com.abc.common;

import com.abc.account.AccountInterface;
import com.abc.transaction.TransactionInterface;


public class WithdrawValidator implements ValidatorInterface {
	
	public void validateTransaction(final AccountInterface account, final TransactionInterface transaction) throws InsufficientBalanceException {
		if(transaction.getAmount() > account.getBalance()){
			throw new InsufficientBalanceException(account.getBalance(), transaction.getAmount());
		}
	}

}