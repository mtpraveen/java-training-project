package com.epam.traning.knight;

import java.io.IOException;

import com.epam.traning.exception.WeaponException;
import com.epam.traning.main.Reader;
import com.epam.traning.store.Store;
import com.epam.traning.weapon.Weapon;

/**
 * 
 * @author Roman
 * 
 */
public class Knight {

	Reader reader = new Reader();
	private Store equipment = new Store();
	private double cash;
	int totalPrice;

	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public double addCash() throws IOException, NumberFormatException {
		try {
			cash = Double.parseDouble(reader.reader(toString()));

			while (cash <= 0) {
				System.out.println("Please, retype value for the khight cash");
					cash = Double.parseDouble(reader.reader(toString()));
			}

		} catch (NumberFormatException e) {
			System.out.println("Cash can't be a string");
		}
		reader.bufferedReader.close();
		return cash;
	}

	/**
	 * 
	 * @return
	 */
	public double getCash() {
		return cash;
	}

	public double getPrice() {
		return totalPrice;
	}

	/**
	 * 
	 * @param weapon
	 * @return
	 * @throws WeaponException
	 */
	public boolean buyThis(Weapon weapon) throws WeaponException {
		double price = weapon.getPrice();
		if (cash >= price) {
			equipment.addAmm(weapon, price);
			totalPrice += weapon.getPrice();
			cash = cash - price;
			return true;
		}
		return false;
	}

}
