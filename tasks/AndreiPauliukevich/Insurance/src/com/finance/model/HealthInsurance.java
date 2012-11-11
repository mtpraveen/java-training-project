package com.finance.model;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class HealthInsurance extends Insurance {

	/**
	 * Default constructor
	 */
	public HealthInsurance() {
		super();
	}

	/**
	 * @param title
	 * @param price
	 * @param risk
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 */
	public HealthInsurance(String title, double price, int risk,
			String firstName, String middleName, String lastName) {
		super(title, price, risk);
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	/** Hold first name */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HealthInsurance [firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HealthInsurance other = (HealthInsurance) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		return true;
	}
}
