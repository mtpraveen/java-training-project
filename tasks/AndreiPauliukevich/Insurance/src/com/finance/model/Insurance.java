package com.finance.model;

/**
 * 
 * @author Andrei Pauliukevich
 * 
 */
public abstract class Insurance {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Insurance [title=" + title + ", price=" + price + ", risk="
				+ risk + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + risk;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (risk != other.risk)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
