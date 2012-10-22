package com.finance.insuranceLiability;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
abstract public class Insurance {
	/** Hold title */
	private String title;
	/** Hold price */
	private double price;
	/** Hold risk */
	private int risk;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the risk
	 */
	public int getRisk() {
		return risk;
	}

	/**
	 * @param risk
	 *            the risk to set
	 */
	public void setRisk(int risk) {
		this.risk = risk;
	}

}
