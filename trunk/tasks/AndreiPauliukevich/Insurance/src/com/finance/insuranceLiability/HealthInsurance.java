package com.finance.insuranceLiability;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class HealthInsurance extends Insurance {

	/** Hold the first name */
	private String firstName;

	/** Hold the middle name */
	private String middleName;

	/** Hold the last name */
	private String lastName;

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName
	 *            the middle name to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the last name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
