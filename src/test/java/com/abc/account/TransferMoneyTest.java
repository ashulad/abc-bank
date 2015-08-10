package com.abc.account;

import static org.junit.Assert.assertEquals;
import mockit.Injectable;
import mockit.NonStrictExpectations;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Test;

import com.abc.common.BankException;
import com.abc.common.InsufficientBalanceException;
import com.abc.common.TransferFailedException;
import com.abc.transaction.Deposit;

public class TransferMoneyTest {
	
	private AccountInterface checkingAccount;
	
	private AccountInterface savingAccount;
	
	@Injectable
	private AccountInterface mockedSavingAccount;
	
	@Before
	public void setUp(){
		checkingAccount = new CheckingAccount("C10001");
		savingAccount = new SavingsAccount("S20001");
		final Deposit deposit = new Deposit(100.00);
		checkingAccount.depositMoney(deposit);
	}
	
	@Test
	public void testTransferFromCheckingToSaving() throws BankException{
		checkingAccount.transferMoney(savingAccount, 60.00);
		assertEquals(40.00, checkingAccount.getBalance(), 0);
		assertEquals(60.00, savingAccount.getBalance(), 0);
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testGetNotEnoughBalanceException() throws BankException{
		checkingAccount.transferMoney(savingAccount, 101.00);
	}
	
	@Test(expected = TransferFailedException.class)
	public void testGetTransferFailedException() throws BankException{
		new NonStrictExpectations() {{
			mockedSavingAccount.depositMoney((Deposit) any);
			result = new Exception();
		}};
		
		checkingAccount.transferMoney(mockedSavingAccount, 60.00);
	}
	
	@Test
	public void testReturnTheMoneyToSourceAccount(){
		new NonStrictExpectations() {{
			mockedSavingAccount.depositMoney((Deposit) any);
			result = new Exception();
		}};
		
		try {
			checkingAccount.transferMoney(mockedSavingAccount, 60.00);
		} catch (BankException e) {
			final String message = e.getMessage();
			assertEquals("Transfer failed due to unexpected reasons, please contact the customer support.", message);
		}
		
		assertEquals(100.00, checkingAccount.getBalance(), 0);
		assertEquals(0.00, savingAccount.getBalance(), 0);
		
		new Verifications(){{
			Deposit deposit;
			mockedSavingAccount.depositMoney(deposit = withCapture());
			assertEquals(60.00, deposit.getAmount(), 0);
		}};
	}
}

