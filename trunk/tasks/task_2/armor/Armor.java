/**
 * @author Alexander Liashcuk
 */
package armor;

/**
 * Class descripting knights armor.
 * 
 * @author Alexander
 * 
 */
public abstract class Armor{

	/**
	 * Armor cost, gold
	 */
	private int cost;
	
	/**
	 * Value of defence ability, %
	 */
	private int defenceValue;
	
	/**
	 * Armor weight, kg
	 */
	private double weight;
	
	/**
	 * Armor Material
	 */
	private String material;

	public Armor(int cost, int defenceValue, double weight, String material) {
		this.setCost(cost);
		this.setDefenceValue(defenceValue);
		this.setMaterial(material);
		this.setWeight(weight);
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDefenceValue() {
		return defenceValue;
	}

	public void setDefenceValue(int defenceValue) {
		this.defenceValue = defenceValue;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}
	
}
