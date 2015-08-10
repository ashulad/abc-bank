package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.customer.CustomerInterface;


public class Bank {
	
    private List<CustomerInterface> customerList;

    public Bank() {
        customerList = new ArrayList<CustomerInterface>();
    }

    public void addCustomer(final CustomerInterface customer) {
        customerList.add(customer);
    }

    public String getCustomerSummary() {
    	final StringBuilder statementBuilder = new StringBuilder();
    	for(final CustomerInterface customer : customerList){
    		statementBuilder.append(customer.getStatement());
    		statementBuilder.append("\n");
    	}
    	return statementBuilder.toString();
    }

    public double getTotalInterestPaid() {
        double total = 0;
        for(final CustomerInterface customer: customerList){
            total += customer.getTotalInterestEarned();
        }
        return total;
    }

    public int getCustomerCount(){
    	return customerList.size();
    }
}

