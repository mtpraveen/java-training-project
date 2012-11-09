package armor;

public class Hauberk extends Armor implements Comparable<Hauberk> {
	private int chestVolume;
	private int ringSize;

	public Hauberk(int cost, int defenceValue, double weight, String material,
			int chestVolume, int ringSize) {
		super(cost, defenceValue, weight, material);
		this.setChestVolume(chestVolume);
		this.setRingSize(ringSize);
	}

	public int getChestVolume() {
		return chestVolume;
	}

	public void setChestVolume(int chestVolume) {
		this.chestVolume = chestVolume;
	}

	public int getRingSize() {
		return ringSize;
	}

	public void setRingSize(int ringSize) {
		this.ringSize = ringSize;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + chestVolume;
		result = prime * result + ringSize;
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
		Hauberk other = (Hauberk) obj;
		if (chestVolume != other.chestVolume)
			return false;
		if (ringSize != other.ringSize)
			return false;
		return true;
	}

	@Override
	public int compareTo(Hauberk o) {
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
