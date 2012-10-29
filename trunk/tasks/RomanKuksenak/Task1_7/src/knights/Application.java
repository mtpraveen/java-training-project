package knights;

/**
 * 
 * @author Roman
 * 
 */
public class Application {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Knights knights = new Knights();
		knights.addCash();
		System.out.println("Knights have:"+Math.rint(100.0 * knights.getCash()) / 100.0+"$");
		Sword sword = new Sword(45);
		Shield shield = new Shield(25);
		Armor armor = new Armor(55);
		Equipment equipment = new Equipment();

		if (equipment.addAmm(sword, knights.getCash()) != null) {
			knights.buyThis(sword.getPrice());
		}
		
		// test: add second bow
		/*
		 * if (equipment.addAmm(sword, knights.getCash()) != null) {
		 * knights.buyThis(sword.getPrice()); }
		 */

		if (equipment.addAmm(shield, knights.getCash()) != null) {
			knights.buyThis(shield.getPrice());
		}

		if (equipment.addAmm(armor, knights.getCash()) != null) {
			knights.buyThis(armor.getPrice());
		}

		equipment.sortEquipment();
		equipment.searchEquipment();

	}
}
