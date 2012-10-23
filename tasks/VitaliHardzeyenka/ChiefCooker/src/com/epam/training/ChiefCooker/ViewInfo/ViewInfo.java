package com.epam.training.ChiefCooker.ViewInfo;

import com.epam.training.ChiefCooker.Constants.ViewInfoConstants;
import com.epam.training.ChiefCooker.Vegetable.*;

/**
 * @author EXUMLOKE
 * Print all information about salad: vegetables in it, weight of every one, total calories.
 */
public class ViewInfo {
	static private Calculations calculations = Calculations.getInstatce();
	
	public static void printInfo(Salad salad) {
		
		// Print information about every vegetable.
		for (AbstractVegetable vegetable : salad.getVegetablesSalad()) {
			System.out.println(ViewInfoConstants.WEIGHT_OF_VEGETABLE_IS + 
							   Math.round(vegetable.getWeightOfVegetable()) + 
							   ViewInfoConstants.CALORIES_IN_ONE_HUNDRED_GRAMS_ARE + 
							   Math.round(vegetable.getCalorieInOneHundredGramms()));
		}
		
		System.out.println(ViewInfoConstants.OIL_ADDED); // info about oil
		System.out.println(ViewInfoConstants.VALUE_OF_CALORIES + Math.round(calculations.calculationsCaloriesInSalad(salad))); // total calories		
	}
}
