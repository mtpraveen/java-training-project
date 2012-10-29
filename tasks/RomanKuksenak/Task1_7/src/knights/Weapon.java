package knights;

/**
 * 
 * @author Roman
 * 
 */
public class Weapon {
	private String name;
	private double price;

	protected Weapon(double price, String name) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
}
