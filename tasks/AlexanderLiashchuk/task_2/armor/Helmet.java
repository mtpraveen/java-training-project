package armor;

/**
 * Класс описывает шлем.
 * 
 * @author Alexander
 */
public class Helmet extends Armor implements Comparable<Helmet> {
	private int headVolume;

	public Helmet(int cost, int defenceValue, double weight, String material,
			int headVolume) {
		super(cost, defenceValue, weight, material);
		this.setWeight(weight);
	}

	public int getHeadVolume() {
		return headVolume;
	}

	public void setHeadVolume(int headVolume) {
		this.headVolume = headVolume;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + headVolume;
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
		Helmet other = (Helmet) obj;
		if (headVolume != other.headVolume)
			return false;
		return true;
	}

	@Override
	public int compareTo(Helmet o) {
		if (this.getCost() > o.getCost())
			return 1;
		else if (this.getCost() < o.getCost())
			return -1;
		else if (this.getDefenceValue() > o.getDefenceValue())
			return 1;
		else if (this.getDefenceValue() < o.getDefenceValue())
			return -1;
		else if (this.getWeight() < o.getWeight())
			return 1;
		else if (this.getWeight() > o.getWeight())
			return -1;
		return 0;
	}

}
