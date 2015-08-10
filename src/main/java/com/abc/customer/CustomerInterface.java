package com.abc.customer;

import com.abc.account.AccountInterface;


public interface CustomerInterface {

    public String getId();
    public String getDisplayName();
    public String getStatement();
    public double getTotalInterestEarned();
    public int getNumberOfAccounts();

    public void openAccount(final AccountInterface account);


}


