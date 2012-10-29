package knights;

/**
 * 
 * @author Roman
 * 
 */
public class Armor extends Weapon {
	private static String name = "Armor";

	/**
	 * 
	 * @param price
	 */
	protected Armor(double price) {
		super(price, name);
	}
}
