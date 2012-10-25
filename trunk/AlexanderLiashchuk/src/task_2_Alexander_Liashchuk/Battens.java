package task_2_Alexander_Liashchuk;

/**
 * Класс описывает латы.
 * 
 * @author Alexander
 * 
 */
public class Battens extends Armor {
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
}
