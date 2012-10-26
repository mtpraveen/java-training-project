package com.finance.insuranceLiability;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public class VehicleInsurance extends Insurance {

	/** Hold model name */
	private String modelName;

	/** Hold year manufactured */
	private int yearManufactured;

	/**
	 * Default constructor
	 */
	public VehicleInsurance() {
		super();
		// TODO Auto-generated constructor stub
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

}
