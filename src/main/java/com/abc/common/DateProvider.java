package com.abc.common;

import java.util.Calendar;
import java.util.Date;


public final class DateProvider {
	
    private static DateProvider instance = new DateProvider();
    
    private DateProvider(){}

    public static DateProvider getInstance() {
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public int daysDifference(final Date startingDate, final Date endingDate){
    	long timeDiff = endingDate.getTime() - startingDate.getTime();
    	long noOfDays = (((timeDiff / 1000) / 60) / 60) / 24;
    	return (int)noOfDays;
    }


}