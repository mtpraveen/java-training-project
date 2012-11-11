package com.finance.model;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class VehicleInsurance extends Insurance {

	/**
	 * Default constructor
	 */
	public VehicleInsurance() {
		super();
	}

	/**
	 * @param title
	 * @param price
	 * @param risk
	 * @param modelName
	 * @param yearManufactured
	 */
	public VehicleInsurance(String title, double price, int risk,
			String modelName, int yearManufactured) {

		super(title, price, risk);
		this.modelName = modelName;
		this.yearManufactured = yearManufactured;
	}

	/** Hold model name */
	private String modelName;

	/** Hold year manufactured */
	private int yearManufactured;

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the model name to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the yearManufactured
	 */
	public int getYearManufactured() {
		return yearManufactured;
	}

	/**
	 * @param yearManufactured
	 *            the year manufactured to set
	 */
	public void setYearManufactured(int yearManufactured) {
		this.yearManufactured = yearManufactured;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VehicleInsurance [modelName=" + modelName
				+ ", yearManufactured=" + yearManufactured + "]";
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
				+ ((modelName == null) ? 0 : modelName.hashCode());
		result = prime * result + yearManufactured;
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
		VehicleInsurance other = (VehicleInsurance) obj;
		if (modelName == null) {
			if (other.modelName != null)
				return false;
		} else if (!modelName.equals(other.modelName))
			return false;
		if (yearManufactured != other.yearManufactured)
			return false;
		return true;
	}

}
