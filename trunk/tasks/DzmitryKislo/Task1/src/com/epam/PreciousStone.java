package com.epam;

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

}
