package com.epam.traning.weapon;

/**
 * 
 * @author Roman
 * 
 */
public class Sword extends Weapon {
	private static String NAME = "Sword";

	/**
	 * 
	 * @param price
	 */

	public Sword(double price) {
		super(price, NAME);
	}
}
