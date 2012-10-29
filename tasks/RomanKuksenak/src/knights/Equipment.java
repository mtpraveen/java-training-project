package knights;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Roman
 * 
 */
public class Equipment {
	private double price;
	List<Weapon> equipment = new ArrayList<Weapon>();
	private int flag = 0;

	/**
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * add ammunition
	 * 
	 * @param weapon
	 * @param cash
	 * @return
	 */
	public Equipment addAmm(Weapon weapon, double cash) {
		if (cash < weapon.getPrice()) {
			System.out.println("There is no money: " + weapon.getName() + " "
					+ weapon.getPrice());
			return null;
		} else {
			for (int i = 0; i < equipment.size(); i++) {
				if (equipment.get(i).getName().equals(weapon.getName())) {
					flag = 1;
				}
			}

			if (flag == 1) {
				System.out.println("The subject " + weapon.getName() + " "
						+ weapon.getPrice() + " is already added");
				flag = 0;
				return null;
			} else {
				equipment.add(weapon);
				price += weapon.getPrice();
				System.out.println(weapon.getName() + " " + weapon.getPrice()
						+ " - added");
				return this;
			}
		}
	}

	/**
	 * sort equipment by price
	 */
	public void sortEquipment() {
		System.out.println("");
		System.out.println("Equipment list: ");
		for (int i = 0; i < equipment.size(); i++) {
			System.out.println(equipment.get(i).getName() + " "
					+ equipment.get(i).getPrice());
		}

		System.out.println("");
		System.out.println("Total price: " + this.getPrice());

		// sort
		for (int a = 0; a < equipment.size(); a++) {
			for (int i = 0; i < equipment.size() - 1; i++) {
				if (equipment.get(i).getPrice() > equipment.get(i + 1)
						.getPrice()) {
					Weapon bufer;
					bufer = equipment.get(i);
					equipment.set(i, equipment.get(i + 1));
					equipment.set(i + 1, bufer);
				}
			}
		}
		System.out.println("");
		System.out.println("Equipment list after sort by price: ");
		for (int i = 0; i < equipment.size(); i++) {
			System.out.println(equipment.get(i).getName() + " "
					+ equipment.get(i).getPrice());
		}
	}

	/**
	 * search by price (from 20 to 60)
	 */
	public void searchEquipment() {
		System.out.println("");
		System.out.println("Result of search by price (20...60): ");
		for (int i = 0; i < equipment.size(); i++) {
			if (equipment.get(i).getPrice() > 20
					&& equipment.get(i).getPrice() < 60) {
				System.out.println(equipment.get(i).getName() + " "
						+ equipment.get(i).getPrice());
			}
		}
	}

}