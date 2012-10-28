package com.epam.training.ChiefCooker.Vegetable;

/**
 * @author EXUMLOKE
 * Abstract class of vegetables. In this class store the main information 
 * about all vegetables that will use for salad building.
 * All vegetables in inheritance chain extends property of this class.
 */
abstract public class AbstractVegetable {
	private double weightOfVegetable; // weight of vegetable count that will use for salad
	private double calorieInOneHundredGramms; // calorie of vegetable in 100 grams for calculate salad calorie content	
	
	// Constructor.
	public AbstractVegetable() { }
	
	/**
	 * Constructor.
	 * @param weightOfVegetable
	 * @param calorieInOneHundredGramms
	 */
	public AbstractVegetable(double weightOfVegetable, double calorieInOneHundredGramms) {		
		setWeightOfVegetable(weightOfVegetable);
		setCalorieInOneHundredGramms(calorieInOneHundredGramms);
	}
	
	/**
	 * Set the value of private field weightOfVegetable.
	 * @param weightOfVegetable - count of vegetable
	 */
	public void setWeightOfVegetable(double weightOfVegetable) {
		if (weightOfVegetable >= 0) { // weight must be a positive number
			this.weightOfVegetable = weightOfVegetable;
		} else {
			this.weightOfVegetable = 0;
		}
	}
	
	/**
	 * @return weightOfVegetable
	 */
	public double getWeightOfVegetable() {
		return this.weightOfVegetable;
	}
	
	/**
	 * Set the value of private field calorieInOneHundredGramms.
	 * @param calorieInOneHundredGramms
	 */
	public void setCalorieInOneHundredGramms(double calorieInOneHundredGramms) {
		if (calorieInOneHundredGramms >= 0) { // count of calorie must be a positive number
			this.calorieInOneHundredGramms = calorieInOneHundredGramms;
		} else {
			this.calorieInOneHundredGramms = 0;
		}
	}
	
	/**
	 * @return calorieInOneHundredGramms
	 */
	public double getCalorieInOneHundredGramms() {
		return this.calorieInOneHundredGramms;
	}
	
	@Override
	public boolean equals(Object object) {
		
//		if (!object.equals(null)) {
			if (this.getCalorieInOneHundredGramms() == ((AbstractVegetable) object).getCalorieInOneHundredGramms() && 
					this.getWeightOfVegetable() == ((AbstractVegetable) object).getWeightOfVegetable() &&
					this.getClass().getName() == ((AbstractVegetable) object).getClass().getName()) {
				return true;
			} else {
				return false;
			}
//		} else {
//			return false;
//		}
	}
}
