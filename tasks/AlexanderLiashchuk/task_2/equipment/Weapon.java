/**
 * @author Alexander Liashchuk
 */
package equipment;

/**
 * Class descripting knights weapon.
 * 
 * @author Alexander
 * 
 */
public class Weapon extends Equipment implements Comparable<Weapon> {

	/**
	 * Value of offence ability, %
	 */
	private int offenceValue;

	/**
	 * Weapon name
	 */
	private String name;

	public Weapon(int cost, int offenceValue, double weight, String material,
			String name) {
		super(cost, weight, material);
		this.setOffenceValue(offenceValue);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	public int getOffenceValue() {
		return offenceValue;
	}

	public void setOffenceValue(int offenceValue) {
		this.offenceValue = offenceValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + offenceValue;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Weapon other = (Weapon) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (offenceValue != other.offenceValue)
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
