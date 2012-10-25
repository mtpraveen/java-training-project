package task_2_Alexander_Liashchuk;

public class Hauberk extends Armor {
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

}
