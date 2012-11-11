package com.epam.traning.store;

import java.util.ArrayList;
import java.util.List;

import com.epam.traning.exception.WeaponException;
import com.epam.traning.weapon.Armor;
import com.epam.traning.weapon.Shield;
import com.epam.traning.weapon.Sword;
import com.epam.traning.weapon.Weapon;


/**
 * 
 * @author Roman
 * 
 */
public class Store {

	private Sword sword = new Sword(30);
	private Shield shield = new Shield(25);
	private Armor armor = new Armor(55);

	private double price;
	List<Weapon> equipment = new ArrayList<Weapon>();
	private int flag = 0;

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	public Sword getSword() {
		return sword;
	}

	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * add equipment to store
	 */
	public void init() {
		equipment.add(getSword());
		equipment.add(getShield());
		equipment.add(getArmor());
	}

	/**
	 * 
	 * @param weapon
	 * @param cash
	 * @return
	 * @throws WeaponException
	 * @throws CashException
	 */
	public Store addAmm(Weapon weapon, double cash) throws WeaponException {
		if (cash < weapon.getPrice()) {
			
		} else {
			for (int i = 0; i < equipment.size(); i++) {
				if (equipment.get(i).getName().equals(weapon.getName())) {
					flag = 1;
				}
			}

			if (flag == 1) {
				flag = 0;

				throw new WeaponException(null);

			} else {
				equipment.add(weapon);
				price += weapon.getPrice();
				System.out.println(weapon.getName() + " " + weapon.getPrice()
						+ " - added");
				return this;
			}
		}
		return null;
	}

	/**
	 * sort equipment by price
	 */

	public void sortEquipment() {
		System.out.println("");
		System.out.println("Equipment list: ");
		int size = equipment.size();
		for (int i = 0; i < size; i++) {
			System.out.println((equipment.get(i)).getName() + " "
					+ (equipment.get(i)).getPrice());
		}

		System.out.println("");
		// sort
		for (int a = 0; a < size; a++) {
			for (int i = 0; i < size - 1; i++) {
				if ((equipment.get(i)).getPrice() > (equipment.get(i + 1))
						.getPrice()) {
					Weapon bufer;
					bufer = (Weapon) equipment.get(i);
					equipment.set(i, equipment.get(i + 1));
					equipment.set(i + 1, bufer);
				}
			}
		}
		System.out.println("");
		System.out.println("Equipment list after sort by price: ");
		for (int i = 0; i < size; i++) {
			System.out.println(((Weapon) equipment.get(i)).getName() + " "
					+ (equipment.get(i)).getPrice());
		}
	}

	/**
	 * search by price (from 20 to 60)
	 */
	public void searchEquipment() {
		System.out.println("");
		System.out.println("Result of search by price (20...50): ");
		for (int i = 0; i < equipment.size(); i++) {
			if (equipment.get(i).getPrice() > 20
					&& equipment.get(i).getPrice() < 50) {
				System.out.println(equipment.get(i).getName() + " "
						+ equipment.get(i).getPrice());
			}
		}
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public Shield getShield() {
		return shield;
	}

}