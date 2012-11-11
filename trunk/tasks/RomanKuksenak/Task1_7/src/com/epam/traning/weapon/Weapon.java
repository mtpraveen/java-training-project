package com.epam.traning.weapon;

/**
 * 
 * @author Roman
 * 
 */
public class Weapon {
	private String name;
	private double price;

	/**
	 * 
	 * @param price
	 * @param name
	 */
	protected Weapon(double price, String name) {
		try {
			this.name = name;
			this.price = price;
		} catch (IllegalArgumentException e) {
			System.out.println("Method get illegal argument!");
		}
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}
