/**
 * 
 */
package edu.XML.Less;

/**
 * @author epam0003
 * 
 */
public class Ingredients {
	private boolean water;
	private int sugar;
	private int fructose;
	private String chocolateType;
	private int vanillin;
	/**
	 * @return the water
	 */
	public boolean isWater() {
		return water;
	}
	/**
	 * @param water the water to set
	 */
	public void setWater(boolean water) {
		this.water = water;
	}
	/**
	 * @return the sugar
	 */
	public int getSugar() {
		return sugar;
	}
	/**
	 * @param sugar the sugar to set
	 */
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
	/**
	 * @return the fructose
	 */
	public int getFructose() {
		return fructose;
	}
	/**
	 * @param fructose the fructose to set
	 */
	public void setFructose(int fructose) {
		this.fructose = fructose;
	}
	/**
	 * @return the chocolateType
	 */
	public String getChocolateType() {
		return chocolateType;
	}
	/**
	 * @param chocolateType the chocolateType to set
	 */
	public void setChocolateType(String chocolateType) {
		chocolateType = chocolateType;
	}
	/**
	 * @return the vanillin
	 */
	public int getVanillin() {
		return vanillin;
	}
	/**
	 * @param vanillin the vanillin to set
	 */
	public void setVanillin(int vanillin) {
		this.vanillin = vanillin;
	}
	
	/**
	 * 
	 */
	public Ingredients() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param water
	 * @param sugar
	 * @param fructose
	 * @param chocolateType
	 * @param vanillin
	 */
	public Ingredients(boolean water, int sugar, int fructose,
			String chocolateType, int vanillin) {
		super();
		this.water = water;
		this.sugar = sugar;
		this.fructose = fructose;
		this.chocolateType = chocolateType;
		this.vanillin = vanillin;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ingredients [water=" + water + ", sugar=" + sugar
				+ ", fructose=" + fructose + ", ChocolateType=" + chocolateType
				+ ", vanillin=" + vanillin + "]";
	}
	
}
