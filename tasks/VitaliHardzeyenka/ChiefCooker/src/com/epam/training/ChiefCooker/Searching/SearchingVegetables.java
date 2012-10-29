package com.epam.training.ChiefCooker.Searching;

import java.util.ArrayList;
import com.epam.training.ChiefCooker.Vegetable.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SearchingVegetables {
	
	/**
	 * Search vegetables in the salad according to conditions. 
	 * @param salad - object of salad with vegetables
	 * @param condition - for example: "weight > 100, calorie < 50"
	 * @return
	 */
	public static ArrayList<AbstractVegetable> searchVegetablesInSalad(Salad salad, String condition) {
		ArrayList<AbstractVegetable> vegetableList = new ArrayList<>(); // list of find vegetables that do for allS conditions
		ArrayList<AbstractVegetable> vegetablesFitToOneCondition = new ArrayList<>(); // list of vegetables that do for one condition

		// Patters for searching
		Pattern weightCaloriePattern = Pattern.compile("(weight|calorie)(\\s*)(>|<|=)(\\s*)(\\d*)");
		Pattern numberPattern = Pattern.compile("(\\d*)");
		Pattern comparationPattern = Pattern.compile(">|<|=");

		// Matcher
		Matcher weightCalorieMatcher = weightCaloriePattern.matcher(condition);
		Matcher numberMatcher;
		Matcher compatationMatcher;

		Double value = 0.0; // value found in the condition string
		int conditionsNumber = 0; // flag for checking is need to find vegetables by one condition or two one

		while (weightCalorieMatcher.find()) { // find conditions for comparison. For example: weight > 100 or calorie = 50
			
			conditionsNumber++; // count number of conditions
			numberMatcher = numberPattern.matcher(weightCalorieMatcher.group()); // find all entry number in the string
			
			// Convert number from string to double
			while (numberMatcher.find()) {
				if (!numberMatcher.group().isEmpty()) {
					value = Double.valueOf(numberMatcher.group());
				}
			}

			compatationMatcher = comparationPattern.matcher(weightCalorieMatcher.group()); // find <, >, =

			while (compatationMatcher.find()) { // find comparator in the current condition string
				if (weightCalorieMatcher.group().contains("weight")) { // if field for comparison is weight
					
					// for every vegetable in salad compare..
					for(AbstractVegetable vegetable : salad.getVegetablesSalad()) {
						vegetablesFitToOneCondition.addAll(returnVegetableMatchToCondition(vegetable, vegetable.getWeightOfVegetable(), value, compatationMatcher.group()));
					}
				} else if (weightCalorieMatcher.group().contains("calorie")) { // if field for comparison is calorie
					for(AbstractVegetable vegetable : salad.getVegetablesSalad()) {
						vegetablesFitToOneCondition.addAll(returnVegetableMatchToCondition(vegetable, vegetable.getCalorieInOneHundredGramms(), value, compatationMatcher.group()));
					}
				}
			}
		}
		
		if (conditionsNumber == 1) { // searching by one condition
			vegetableList.addAll(vegetablesFitToOneCondition);
		} else { // find vegetables in vegetablesFitToOneCondition that fit to both conditions
			for (int i = 0; i < vegetablesFitToOneCondition.size(); i++) {
				for (int j = i; j < vegetablesFitToOneCondition.size(); j++) {
					if (i != j) {
						if (vegetablesFitToOneCondition.get(i).equals(vegetablesFitToOneCondition.get(j))) {
							vegetableList.add(vegetablesFitToOneCondition.get(i));
						}
					}
				}
			}
		}		

		return vegetableList;
	}
	
	/**
	 * Compare field according to comparator.
	 * Return vegetable from salad, if it fit to condition.
	 * @param vegetable - vegetable which field compare 
	 * @param field - field which compare with value
	 * @param value - number which compare with field
	 * @param comparator - <, > or =
	 * @return
	 */
	private static ArrayList<AbstractVegetable> returnVegetableMatchToCondition(AbstractVegetable vegetable, Object field, double value, String comparator) {
		ArrayList<AbstractVegetable> vegetableList = new ArrayList<>();
		
		switch (comparator) {
		case ">":
			if ((double) field > value) {
				vegetableList.add(vegetable);
			}
			break;
		case "<":
			if ((double) field < value) {
				vegetableList.add(vegetable);
			}
			break;
		case "=":
			if ((double) field == value) {
				vegetableList.add(vegetable);
			}
			break;
		default:
			break;
		}
		return vegetableList;
	}
}