package com.abc.customer;

public class CustomerInfo {
	
	private String firstName;
	private String lastName;
	
	public CustomerInfo(final String firstName, final String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getFullName() {
		final StringBuilder strBuild = new StringBuilder();
		strBuild.append(lastName);
		strBuild.append(", ");
		strBuild.append(firstName);
		return strBuild.toString();
	}
	
}

