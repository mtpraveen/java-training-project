package com.epam;

public class SemiPreciousStone extends Stone {

	private static int countSemiPreciousStone;
	private String name;
	private double price;

	static {
		countSemiPreciousStone = 0;
	}

	public SemiPreciousStone(String name, double weight, double price) {
		super(weight);
		++countSemiPreciousStone;
		this.name = name + " " + countSemiPreciousStone;
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

	public static int getCountSemiPreciousStone() {
		return countSemiPreciousStone;
	}

	@Override
	public String toString() {
		return name + ", price=" + price + ", " + super.toString();
	}

}
