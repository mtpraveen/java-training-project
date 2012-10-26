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
	 * Compare all values from the current enumeration with 
	 * string as enumeration value, return the result of the comparation.
	 * @param kindOfOil
	 * @return
	 */
	public static boolean contains(String kindOfOil) {
		boolean isContains = false;
		
		for (KindsOfOil element : KindsOfOil.values()) {
			if (element == KindsOfOil.valueOf(kindOfOil)) {
				isContains = true;
			}
		}
		return isContains;
	}
	
	/**
	 * Get the value of appropriate oil.
	 * @return
	 */
	double getCaloriesInOneHundredGrammsOfOil() {
		return this.caloriesInOneHundredGrammsOfOil;
	}
	
}
