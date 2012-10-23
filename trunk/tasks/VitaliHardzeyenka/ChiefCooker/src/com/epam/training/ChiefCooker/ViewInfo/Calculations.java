package com.epam.training.ChiefCooker.ViewInfo;

import com.epam.training.ChiefCooker.Oil.*;
import com.epam.training.ChiefCooker.Vegetable.Salad;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;

/**
 * Singleton.
 * @author EXUMLOKE
 * This class used for calculation mass of salad and it calories value.
 */
final public class Calculations {
	
	private static Calculations INSTANCE = null;
	private double allCaloriesInSalad; // calories value of salad
	private double massOfVegetablesInSalad; // weight of salad
	
	/**
	 * Constructor.
	 */
	private Calculations() {}
	
	/**
	 * Get exemplar of current class.
	 * @return
	 */
	public static Calculations getInstatce() {
		if (INSTANCE == null) {
			INSTANCE = new Calculations();
		}
		
		return INSTANCE;
	}
	
	private void setAllCaloriesInSalad(double allCaloriesInSalad) {
		this.allCaloriesInSalad = allCaloriesInSalad;
	}
	
	private double getAllCaloriesInSalad() {
		return this.allCaloriesInSalad;
	}
	
	private void setMassOfVegetablesInSalad(double massOfSalad) {
		this.massOfVegetablesInSalad = massOfSalad;
	}
	
	private double getMassOVegetablesInSalad() {
		return this.massOfVegetablesInSalad;
	}
	
	private double calculationMassOfVegetablesIsnSalad(Salad salad) {
		setMassOfVegetablesInSalad(0);
		
		for (AbstractVegetable vegetable : salad.getVegetablesSalad()) {
			setMassOfVegetablesInSalad(getMassOVegetablesInSalad() + vegetable.getWeightOfVegetable());
		}
		
		return getMassOVegetablesInSalad();
	}
	
	/**
	 * Calculate the total calories value of all vegetables add oil in the salad. 
	 * @param salad
	 * @return weight of saladS
	 */
	public double calculationsCaloriesInSalad(Salad salad) {
		setAllCaloriesInSalad(0);
		
		// Calculate calorie value for every vegetable.
		for (AbstractVegetable vegetable : salad.getVegetablesSalad()) {
			setAllCaloriesInSalad(getAllCaloriesInSalad() + ((vegetable.getWeightOfVegetable() / (double) 100) * vegetable.getCalorieInOneHundredGramms()));
		}
		
		Oil oil = salad.getOil(); // get oil from the salad
		
		// Add oil's calorie to total calories value.
		oil.setMassOfOil((calculationMassOfVegetablesIsnSalad(salad) / 10)); // calculate how much oil need add to salad subject to total vegetables weight
		setAllCaloriesInSalad((getAllCaloriesInSalad() + (oil.getMassOfOil() / 100.0) * (oil.getCaloriesInOneHundredGrammsOfOil()))); // add oil calorie in the salads
		
		return getAllCaloriesInSalad();
	}
}
