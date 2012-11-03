package com.epam.entity;


public class PreciousStone extends Stone {

	private static int countPreciousStone;
	private String name;
	private double price;

	static {
		countPreciousStone = 0;
	}

	public PreciousStone(String name, double weight, double price) {
		super(weight);
		++countPreciousStone;
		this.name = name + " " + countPreciousStone;
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getCountPreciousStone() {
		return countPreciousStone;
	}

	@Override
	public String toString() {
		return name + ", price=" + price + ", " + super.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PreciousStone other = (PreciousStone) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		return true;
	}

	
}
