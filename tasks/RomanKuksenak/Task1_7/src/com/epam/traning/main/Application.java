package com.epam.traning.main;

import java.io.IOException;

import com.epam.traning.exception.WeaponException;
import com.epam.traning.knight.Knight;
import com.epam.traning.store.Store;

/**
 * 
 * @author Roman
 * 
 */
public class Application {
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws CashException
	 */
	public static void main(String[] args) throws IOException, WeaponException {
		
		Knight knight = new Knight();
		Store store = new Store();

		store.init();
		store.sortEquipment();
		store.searchEquipment();
		
		knight.addCash();
		knight.buyThis(store.getSword());
		knight.buyThis(store.getShield());
		knight.buyThis(store.getArmor());
		

		System.out.println("");
		System.out.println("The knight have:" + knight.getCash());
		System.out.println("Knight's ammunition coast:" + knight.getPrice());

	}
}
