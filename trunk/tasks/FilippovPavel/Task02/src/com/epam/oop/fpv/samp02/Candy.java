/**
 * 
 */
package com.epam.oop.fpv.samp02;

/**
 * @author Pavel
 *
 */

/*
 * Class Candy - class describes a simple candies
 * Basic properties: weight, calorie, sugar, shelfLife, count
 */
public class Candy{
	private String name;
	private double weight, calory, sugar;
	private int shelfLife, count;
	
	public Candy(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount){
		name = cName;
		weight = cWght;
		calory = cCalory;
		sugar = cSugar;
		shelfLife = cShLife;
		count = cCount;
	}
	
	public double getSugarPerGram() {
		return sugar / weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getCalory() {
		return calory;
	}
	public void setCalory(double calory) {
		this.calory = calory;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(int shelfLife) {
		this.shelfLife = shelfLife;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(calory);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + count;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + shelfLife;
		temp = Double.doubleToLongBits(sugar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Candy other = (Candy) obj;
		if (Double.doubleToLongBits(calory) != Double
				.doubleToLongBits(other.calory))
			return false;
		if (count != other.count)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shelfLife != other.shelfLife)
			return false;
		if (Double.doubleToLongBits(sugar) != Double
				.doubleToLongBits(other.sugar))
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
}
