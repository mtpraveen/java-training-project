package com.epam.training.ChiefCooker.Builder;


import java.util.ArrayList;

import com.epam.training.ChiefCooker.Constants.SaladBuilderConstants;
import com.epam.training.ChiefCooker.Cucurbitaceae.Squash;
import com.epam.training.ChiefCooker.InputOutput.ConsoleInputOutput;
import com.epam.training.ChiefCooker.Oil.KindsOfOil;
import com.epam.training.ChiefCooker.Oil.Oil;
import com.epam.training.ChiefCooker.Solanaceae.Pepper;
import com.epam.training.ChiefCooker.Solanaceae.RipenessOfSolanaceae;
import com.epam.training.ChiefCooker.Solanaceae.Tomato;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;
import com.epam.training.ChiefCooker.Vegetable.Salad;
import com.epam.training.ChiefCooker.ViewInfo.ViewInfo;

/**
 * @author Gordeenko
 * Build salad with vegetables. 
 * Read from console vegetables, calorie and weight every one.
 * Output information about salad.
 */
public class SaladBuilder extends AbstractBaseBuilder {
	
	/**
	 * Main method in class. Read information from keyboard and build the salad.
	 */
	public void buildSalad() {
		ConsoleInputOutput consoleInputOutput = ConsoleInputOutput.getInstanse();
		
		// start initialization of local variables
		String[] enumerationVegetablesInSalad; // vegetables is salad as string
		boolean endOfBuilding = false; // true - exit, false - next salad
		ArrayList<AbstractVegetable> collectionOfVegetables = new ArrayList<AbstractVegetable>(); // collection of vegetables in the salad
		KindsOfOil kindOfOil = KindsOfOil.OLIVE; // oil
		
		do { // while don`t need to build salads any more
			
			collectionOfVegetables.clear(); // clean collection of vegetables after previous building
			
			System.out.println(SaladBuilderConstants.BEGIN_BUILD_SALAD); // lets begin to build salad
			System.out.println(SaladBuilderConstants.TYPE_VEGETABLE); // type the vegetable u want to add			
						
			enumerationVegetablesInSalad = consoleInputOutput.readString().split(" "); // read numbers of vegetables from console			
			
			for (int i = 0; i < enumerationVegetablesInSalad.length; i++) {
				switch (enumerationVegetablesInSalad[i]) {
					case "1": // pepper
						
						// Add new vegetable in the salad.
						collectionOfVegetables.add(readSolanaceaeInfo("Pepper", consoleInputOutput));					
						
						break;
					case "2": // tomato
						
						collectionOfVegetables.add(readSolanaceaeInfo("Tomato", consoleInputOutput));
						
						break;
					case "3": // squash						
						
						collectionOfVegetables.add(readCucurbitaceaeInfo("Squash", consoleInputOutput));
						
						break;
					default:
						break;
							
				}
			}
			
			System.out.println(SaladBuilderConstants.CHOOSE_OIL); // type the oil			
			kindOfOil = readKindOfOil(consoleInputOutput);						
			Oil oil = new Oil(kindOfOil); // add oil in the salad			
			
			// Print all info about salad.
			ViewInfo.printInfo(new Salad(oil, collectionOfVegetables));
			
			System.out.println(SaladBuilderConstants.BUILD_ONE_MORE_SALAD); // do u want to build one more salad?			
			if (consoleInputOutput.readChar() == 'n') {
				endOfBuilding = true;
			}			
			
		} while (endOfBuilding == false);
	}
	
	private AbstractVegetable readSolanaceaeInfo(String vegetableName, ConsoleInputOutput consoleInputOutput) {
		System.out.println(String.format("\t%s: ", vegetableName));
		System.out.println(SaladBuilderConstants.TYPE_RIPENESS);
		RipenessOfSolanaceae ripeness = readRipenessOfSolanaceae(consoleInputOutput);
		
		System.out.println(SaladBuilderConstants.TYPE_WEIGHT_OF_VEGETABLE);
		double weightOfVegetable = readWeight(consoleInputOutput);
		
		System.out.println(SaladBuilderConstants.TYPE_CALORIES_ONE_HUNDRED_GRAMS);
		double calorieInOneHundredGramms = readCalorie(consoleInputOutput);
		
		System.out.println(SaladBuilderConstants.TYPE_ABILITY_OF_PULP);
		boolean availabilityOfPulp = readAbilityOfPulp(consoleInputOutput);
		
		AbstractVegetable vegetable;
		if (vegetableName == "Pepper") {
			vegetable = new Pepper(weightOfVegetable, 
												     calorieInOneHundredGramms,
												     ripeness, 
												     availabilityOfPulp);
		} else if (vegetableName == "Tomato") {
			vegetable = new Tomato(weightOfVegetable, 
												     calorieInOneHundredGramms,
												     ripeness, 
												     availabilityOfPulp);
		} else {
			vegetable = null;
		}
		
		return vegetable;
	}
	
	private AbstractVegetable readCucurbitaceaeInfo(String vegetableName, ConsoleInputOutput consoleInputOutput) {
	
		System.out.println(String.format("\t%s: ", vegetableName));
		
		System.out.println(SaladBuilderConstants.TYPE_WEIGHT_OF_VEGETABLE);
		double weightOfVegetable = readWeight(consoleInputOutput);
		
		System.out.println(SaladBuilderConstants.TYPE_CALORIES_ONE_HUNDRED_GRAMS);
		double calorieInOneHundredGramms = readCalorie(consoleInputOutput);
		
		AbstractVegetable vegetable = new Squash(weightOfVegetable, calorieInOneHundredGramms);
		
		return vegetable;
	}
	
	private boolean readAbilityOfPulp(ConsoleInputOutput consoleInputOutput) {		
		
		char ability = consoleInputOutput.readChar();
		
		if (ability == 'y') {
			return true;
		} else if (ability == 'n') {
			return false;
		} else {
			return true;
		}		
	}
	
	private KindsOfOil readKindOfOil(ConsoleInputOutput consoleInputOutput) {
		
		String kindOfOil = consoleInputOutput.readString();
		kindOfOil =  kindOfOil.toUpperCase();
		
		if (kindOfOil.equals("SUNFLOWER")) {
			return KindsOfOil.SUNFLOWER;
		} else if (kindOfOil.equals("OLIVE")) {
			return KindsOfOil.OLIVE;
		} else {
			return KindsOfOil.SUNFLOWER;
		}
	}
	
	private RipenessOfSolanaceae readRipenessOfSolanaceae(ConsoleInputOutput consoleInputOutput) {
		
		String ripeness = consoleInputOutput.readString();
		
		if (ripeness.equals("BAD")) {
			return RipenessOfSolanaceae.BAD;
		} else if (ripeness.equals("NORMAL")) {
			return RipenessOfSolanaceae.NORMAL;			
		} else if (ripeness.equals("GOOD")) {
			return RipenessOfSolanaceae.GOOD;		
		} else {
			return RipenessOfSolanaceae.BAD;
		}
	}
	
	private double readWeight(ConsoleInputOutput consoleInputOutput) {
		try {
			return consoleInputOutput.readDouble();
		} catch(Exception exception) {
			return 0;
		}
	}
	
	private double readCalorie(ConsoleInputOutput consoleInputOutput) {
		try {
			return consoleInputOutput.readDouble();		
		} catch(Exception exception) {
			return 0;
		}		
	}	
}