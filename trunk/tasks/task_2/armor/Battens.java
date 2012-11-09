package armor;

/**
 * Класс описывает латы.
 * 
 * @author Alexander
 * 
 */
public class Battens extends Armor implements Comparable<Battens> {
	private int chestVolume;
	private int thighVolume;
	private int legLength;

	public Battens(int cost, int defenceValue, double weight, String material,
			int chestVolume, int thighVolume, int legLength) {
		super(cost, defenceValue, weight, material);
		this.setChestVolume(chestVolume);
		this.setThighVolume(thighVolume);
		this.setLegLength(legLength);
	}

	public int getChestVolume() {
		return chestVolume;
	}

	public void setChestVolume(int chestVolume) {
		this.chestVolume = chestVolume;
	}

	public int getThighVolume() {
		return thighVolume;
	}

	public void setThighVolume(int thighVolume) {
		this.thighVolume = thighVolume;
	}

	public int getLegLength() {
		return legLength;
	}

	public void setLegLength(int legLength) {
		this.legLength = legLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + chestVolume;
		result = prime * result + legLength;
		result = prime * result + thighVolume;
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
		Battens other = (Battens) obj;
		if (chestVolume != other.chestVolume)
			return false;
		if (legLength != other.legLength)
			return false;
		if (thighVolume != other.thighVolume)
			return false;
		return true;
	}

	@Override
	public int compareTo(Battens o) {
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
