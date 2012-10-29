package knights;

/**
 * 
 * @author Roman
 * 
 */
public class Knights {
	private double cash;

	/**
	 * 
	 * @return
	 */
	public double addCash() {
		cash = Math.random() * 100;
		return cash;
	}

	/**
	 * 
	 * @return
	 */
	public double getCash() {
		return cash;
	}

	/**
	 * 
	 * @param priceOfThis
	 * @return
	 */
	public double buyThis(double priceOfThis) {
		cash = cash - priceOfThis;
		return cash;
	}

}
