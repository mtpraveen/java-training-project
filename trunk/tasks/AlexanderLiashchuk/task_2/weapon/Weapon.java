/**
 * @author Alexander Liashchuk
 */
package weapon;

/**
 * Class descripting knights weapon.
 * 
 * @author Alexander
 * 
 */
public class Weapon implements Comparable<Weapon> {

	/**
	 * Weapon cost, gold
	 */
	private int cost;
	
	/**
	 * Value of offence ability, %
	 */
	private int offenceValue;
	
	/**
	 * Weapon weight, kg
	 */
	private double weight;
	
	/**
	 * Weapon material
	 */
	private String material;
	
	/**
	 * Weapon name
	 */
	private String name;

	public Weapon(int cost, int offenceValue, double weight, String material, 
			String name) {
		this.setCost(cost);
		this.setOffenceValue(offenceValue);
		this.setMaterial(material);
		this.setWeight(weight);
		this.setName(name);
	}
	
	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getOffenceValue() {
		return offenceValue;
	}

	public void setOffenceValue(int offenceValue) {
		this.offenceValue = offenceValue;
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

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result
				+ ((material == null) ? 0 : material.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + offenceValue;
		long temp;
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		if (cost != other.cost)
			return false;
		if (material == null) {
			if (other.material != null)
				return false;
		} else if (!material.equals(other.material))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offenceValue != other.offenceValue)
			return false;
		if (Double.doubleToLongBits(weight) != Double
				.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	public int compareTo(Weapon o) {
		if (this.getCost() > o.getCost())
			return 1;
		else if (this.getCost() < o.getCost())
			return -1;
		else if (this.getOffenceValue() > o.getOffenceValue())
			return 1;
		else if (this.getOffenceValue() < o.getOffenceValue())
			return -1;
		else if (this.getWeight() < o.getWeight())
			return 1;
		else if (this.getWeight() > o.getWeight())
			return -1;
		return 0;
	}

}
