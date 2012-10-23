package com.epam.training.ChiefCooker.Oil;

/**
 * @author EXUMLOKE
 * Enumeration with kinds of oil.
 * The value of every field - its calories of oil.
 */
public enum KindsOfOil {
	OLIVE(85), SUNFLOWER(100);
	
	private double caloriesInOneHundredGrammsOfOil; // calories of oil
	
	/**
	 * Constructor.
	 * @param caloriesInOneHundredGrammsOfOil - calories of oil.
	 */
	KindsOfOil(double caloriesInOneHundredGrammsOfOil) {
		this.caloriesInOneHundredGrammsOfOil = caloriesInOneHundredGrammsOfOil;
	}
	
	/**
	 * Get the value of appropriate oil.
	 * @return
	 */
	double getCaloriesInOneHundredGrammsOfOil() {
		return this.caloriesInOneHundredGrammsOfOil;
	}
	
}
