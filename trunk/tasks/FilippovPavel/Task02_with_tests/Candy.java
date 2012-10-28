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
 * Basic properties: weight, calory, sugar, shelfLife, count
 */
public class Candy{
	String name;
	private double weight, calory, sugar;
	private int shelfLife, count;
	
	Candy(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount){
		name = cName;
		weight = cWght;
		calory = cCalory;
		sugar = cSugar;
		shelfLife = cShLife;
		count = cCount;
	}
	
	public String getName() {
		return name;
	}
	public double getWeight() {
		return weight;
	}
	public double getCalory() {
		return calory;
	}
	public double getSugar() {
		return sugar;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public int getCount() {
		return count;
	}
}
