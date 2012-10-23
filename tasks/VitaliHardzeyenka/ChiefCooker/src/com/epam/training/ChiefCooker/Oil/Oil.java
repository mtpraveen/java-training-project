package com.epam.training.ChiefCooker.Oil;

/**
 * @author EXUMLOKE
 * This class describe different kinds of oil.
 */
public class Oil {
	private double massOfOil; // mass of oil that will add in the salad
	private double caloriesInOneHundredGrammsOfOil; // calories of oil
	private static KindsOfOil kindOfOil; // kind of oil (olive, sunflower) from KindsOfOil enumeration
	
	/**
	 * Constructor.
	 */
	public Oil() {}
	
	/**
	 * Constructor.
	 * @param kindOfOil (olive, sunflower) from enumeration KindsOfOil
	 */
	public Oil(KindsOfOil kindOfOil) {
		if (kindOfOil instanceof KindsOfOil) {
			setCaloriesInOneHundredGrammsOfOil(kindOfOil.getCaloriesInOneHundredGrammsOfOil());
			setKindOfOil(kindOfOil);
		}
	}
	
	public void setCaloriesInOneHundredGrammsOfOil(double caloriesInOneHundredGrammsOfOil){
		this.caloriesInOneHundredGrammsOfOil = caloriesInOneHundredGrammsOfOil;
	}
	
	public double getCaloriesInOneHundredGrammsOfOil() {
		return this.caloriesInOneHundredGrammsOfOil;
	}
	
	public void setKindOfOil(KindsOfOil kindOfOil) {
		Oil.kindOfOil = kindOfOil;
	}
	
	public KindsOfOil getKindOfOil() {
		return Oil.kindOfOil;
	}	

	public void setMassOfOil(double massOfOil){
		this.massOfOil = massOfOil;
	}
	
	public double getMassOfOil(){
		return this.massOfOil;
	}
}
