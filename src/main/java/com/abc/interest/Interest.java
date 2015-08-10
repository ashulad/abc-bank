package com.abc.interest;

import java.text.DecimalFormat;
import java.util.Date;

import com.abc.common.DateProvider;


public abstract class Interest implements InterestInterface{
	
	protected double calculateInterest(final double amount, final double rate, final Date startDate, final Date endDate) {
		final int noOfDays = DateProvider.getInstance().daysDifference(startDate, endDate);
		final double interestAmount = ((((amount * rate) / 100) / 365) * noOfDays);
		return Double.valueOf(new DecimalFormat("#.##").format(interestAmount));
	}

	
}

