/**
 * @author Alexander Liashcuk
 */
package task_2_Alexander_Liashchuk;

/**
 * Class descripting knights armor.
 * 
 * @author Alexander
 * 
 */
public class Armor {

	/**
	 * ��������� ������� �����
	 */
	private int cost;
	
	/**
	 * �������� ����������� �����
	 */
	private int defenceValue;
	
	/**
	 * ��� ������� �����
	 */
	private double weight;
	
	/**
	 * ��������, �� �������� ����������� �����
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
