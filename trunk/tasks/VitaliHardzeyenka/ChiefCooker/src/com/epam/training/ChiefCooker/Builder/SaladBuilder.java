package com.epam.training.ChiefCooker.Builder;


import java.util.ArrayList;

import com.epam.training.ChiefCooker.Constants.SaladBuilderConstants;
import com.epam.training.ChiefCooker.Factory.CucurbitaceaeFactory;
import com.epam.training.ChiefCooker.Factory.SolanaceaeFactory;
import com.epam.training.ChiefCooker.InputOutput.ConsoleInputOutput;
import com.epam.training.ChiefCooker.Oil.KindsOfOil;
import com.epam.training.ChiefCooker.Oil.Oil;
import com.epam.training.ChiefCooker.Solanaceae.RipenessOfSolanaceae;
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
		String enumerationVegetablesInSalad; // vegetables is salad as string
		boolean endOfBuilding = false; // true - exit, false - next salad
		ArrayList<AbstractVegetable> collectionOfVegetables = new ArrayList<AbstractVegetable>(); // collection of vegetables in the salad
		
		do { // while don`t need to build salads any more
			
			collectionOfVegetables.clear(); // clean collection of vegetables after previous building
			
			System.out.println(SaladBuilderConstants.BEGIN_BUILD_SALAD); // lets begin to build salad
			System.out.println(SaladBuilderConstants.TYPE_VEGETABLE); // type the vegetable u want to add			
						
			enumerationVegetablesInSalad = consoleInputOutput.readString(); // read numbers of vegetables from console			
			
			for (int i = 0; i < enumerationVegetablesInSalad.length(); i++) {
				switch (enumerationVegetablesInSalad.charAt(i)) {
					case SaladBuilderConstants.PEPPER_NUMBER: // pepper
						
						// Add new vegetable in the salad.
						collectionOfVegetables.add(createSolanaceae(SaladBuilderConstants.PEPPER, consoleInputOutput));				
						
						break;
					case SaladBuilderConstants.TOMATO_NUMBER: // tomato
						
						collectionOfVegetables.add(createSolanaceae(SaladBuilderConstants.TOMATO, consoleInputOutput));
						
						break;
					case SaladBuilderConstants.SQUASH_NUMBER: // squash						
						
						collectionOfVegetables.add(createCucurbitaceae(SaladBuilderConstants.SQUASH, consoleInputOutput));
						
						break;
					default:
						System.out.println(String.format("%s: %c", SaladBuilderConstants.NO_SUCH_VEGETABLE_MESSAGE, enumerationVegetablesInSalad.charAt(i)));	
						break;							
				}
			}
			
			// Read information about oil that will add to salad.
			Oil oil = createOil(consoleInputOutput);
			
			// Print all info about salad.
			ViewInfo.printInfo(new Salad(oil, collectionOfVegetables));
			
			System.out.println(SaladBuilderConstants.BUILD_ONE_MORE_SALAD); // do u want to build one more salad?			
			endOfBuilding = (consoleInputOutput.readChar() == SaladBuilderConstants.NO_CHAR) ? true : false; 
			
		} while (endOfBuilding == false);
	}
	
	/**
	 * Read information about oil that will add to salad.
	 * @param consoleInputOutput
	 * @return - Oil
	 */
	private Oil createOil(ConsoleInputOutput consoleInputOutput) {
		System.out.println(SaladBuilderConstants.CHOOSE_OIL); // type the oil
		String kindOfOil = consoleInputOutput.readString().toUpperCase();
		
		return KindsOfOil.contains(kindOfOil) ? (new Oil(KindsOfOil.valueOf(kindOfOil))) : null;
	}
	
	/**
	 * Read information form console that needed to create the solanaceae object.
	 * @param vegetableName - name of the vegetable: tomato or pepper
	 * @param consoleInputOutput - object of console input class
	 * @return
	 */
	private AbstractVegetable createSolanaceae(String vegetableName, ConsoleInputOutput consoleInputOutput) {
		
		System.out.println(String.format("\t%s: ", vegetableName));
		
		// Read the major parameters of vegetables.
		ArrayList<Object> majorParameters = readMajorParameters(consoleInputOutput);
		
		System.out.println(SaladBuilderConstants.TYPE_RIPENESS);
		RipenessOfSolanaceae ripeness = readRipenessOfSolanaceae(consoleInputOutput);
		
		System.out.println(SaladBuilderConstants.TYPE_ABILITY_OF_PULP);
		boolean availabilityOfPulp = readAbilityOfPulp(consoleInputOutput);
		
		return SolanaceaeFactory.createSolanaceae(vegetableName, (double) majorParameters.get(0), (double) majorParameters.get(1), ripeness, availabilityOfPulp);
	}
	
	/**
	 * 
	 * @param vegetableName
	 * @param consoleInputOutput
	 * @return
	 */
	private AbstractVegetable createCucurbitaceae(String vegetableName, ConsoleInputOutput consoleInputOutput) {
	
		System.out.println(String.format("\t%s: ", vegetableName));
		
		// Read the major parameters of vegetables.
		ArrayList<Object> majorParameters = readMajorParameters(consoleInputOutput);
		
		return CucurbitaceaeFactory.createCucurbitaceae(vegetableName, (double) majorParameters.get(0), (double) majorParameters.get(1));
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private boolean readAbilityOfPulp(ConsoleInputOutput consoleInputOutput) {		
		char ability;
		do {
			ability = consoleInputOutput.readChar();
		} while (!((ability == SaladBuilderConstants.NO_CHAR) || (ability == SaladBuilderConstants.YES_CHAR)));
		
		return (ability == SaladBuilderConstants.YES_CHAR) ? true : false;
	}
	
	/**
	 * Read from console major parameters (inherent in each vegetable).
	 * First parameter (0) - weight of vegetable.
	 * Second parameter (1) - calories in 100 grams of vegetable. 
	 * @param consoleInputOutput
	 * @return set of the major parameters
	 */
	private ArrayList<Object> readMajorParameters(ConsoleInputOutput consoleInputOutput) {
		
		ArrayList<Object> majorParameters = new ArrayList<>();
		System.out.println(SaladBuilderConstants.TYPE_WEIGHT_OF_VEGETABLE);
		majorParameters.add(readWeight(consoleInputOutput)); // weight
		
		System.out.println(SaladBuilderConstants.TYPE_CALORIES_ONE_HUNDRED_GRAMS);
		majorParameters.add(readCalorie(consoleInputOutput)); // calories
		
		return majorParameters;
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private RipenessOfSolanaceae readRipenessOfSolanaceae(ConsoleInputOutput consoleInputOutput) {
		String ripeness = consoleInputOutput.readString().toUpperCase();
		return RipenessOfSolanaceae.contains(ripeness) ? RipenessOfSolanaceae.valueOf(ripeness) : RipenessOfSolanaceae.BAD ;
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private double readWeight(ConsoleInputOutput consoleInputOutput) {
		return readDouble(consoleInputOutput);
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private double readCalorie(ConsoleInputOutput consoleInputOutput) {
		return readDouble(consoleInputOutput);
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private double readDouble(ConsoleInputOutput consoleInputOutput) {
		try {
			return Math.abs(consoleInputOutput.readDouble()); // weight and calorie must be a positive numbers
		} catch (Exception exception) {
			System.out.println("The input value is not double.");
			return 0;
		}	
	}
}