package com.epam.traning.weapon;


/**
 * 
 * @author Roman
 * 
 */
public class Shield extends Weapon {
	private static String NAME = "Shield";

	/**
	 * 
	 * @param price
	 */
	public Shield(double price) {
		super(price, NAME);
	}
}
