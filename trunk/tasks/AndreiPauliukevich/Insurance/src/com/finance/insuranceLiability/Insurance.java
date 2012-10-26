package com.finance.insuranceLiability;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
abstract public class Insurance implements Cloneable {

	/** Hold title */
	private String title;
	/** Hold price */
	private double price;
	/** Hold risk */
	private int risk;

	/**
	 * Default constructor
	 */
	public Insurance() {

	}

	/**
	 * @param title
	 * @param price
	 * @param risk
	 */
	public Insurance(String title, double price, int risk) {
		super();
		this.title = title;
		this.price = price;
		this.risk = risk;
	}

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

	/**
	 * @param title
	 * @param price
	 * @param risk
	 * @throws CloneNotSupportedException
	 */

	public Insurance clone() throws CloneNotSupportedException {
		return (Insurance) super.clone();
	}
}
