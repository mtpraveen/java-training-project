package com.epam;

public class Stone implements Comparable<Stone> {

	protected double weight;

	public Stone(double weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return 0.0;
	}

	@Override
	public String toString() {
		return "weight=" + weight;
	}

	public int compareTo(Stone obj) {
		if (this.weight < obj.getWeight())
			return -1;
		else if (this.weight > obj.getWeight())
			return 1;
		else
			return 0;

	}

}
