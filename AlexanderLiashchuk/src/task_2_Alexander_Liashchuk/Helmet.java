package task_2_Alexander_Liashchuk;

/**
 * Класс описывает шлем.
 * 
 * @author Alexander
 */
public class Helmet extends Armor {
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

}
