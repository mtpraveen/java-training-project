package com.epam.traning.weapon;


/**
 * 
 * @author Roman
 * 
 */
public class Armor extends Weapon {
	private static String NAME = "Armor";

	/**
	 * 
	 * @param price
	 */
	public Armor(double price) {
		super(price, NAME);
	}
}
