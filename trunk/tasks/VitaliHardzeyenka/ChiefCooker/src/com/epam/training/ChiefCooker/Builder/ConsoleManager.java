package com.epam.training.ChiefCooker.Builder;

import java.util.ArrayList;

import com.epam.training.ChiefCooker.Constants.ConsoleManagerConstants;
import com.epam.training.ChiefCooker.InputOutput.ConsoleInputOutput;
import com.epam.training.ChiefCooker.Oil.Oil;
import com.epam.training.ChiefCooker.Solanaceae.RipenessOfSolanaceae;
import com.epam.training.ChiefCooker.ViewInfo.ViewInfo;

/**
 * Manage the console input for salad building.
 * @author EXUMLOKE
 *
 */
public class ConsoleManager {

	public void readInfoForSalad() {
		
		SaladBuilder saladBuilder = new SaladBuilder();		
		ConsoleInputOutput consoleInputOutput = ConsoleInputOutput.getInstanse();		
		String enumerationVegetablesInSalad; // vegetables is salad as string
		boolean isBuilding = false; // true - exit, false - next salad
		// Parameters for salad.
		ArrayList<Double> majorParameters = new ArrayList<>(); // weight and calories		
		String kindOfOil; // kind of oil
		
		
		do { // while don`t need to build salads any more
			
			System.out.println(ConsoleManagerConstants.BEGIN_BUILD_SALAD); // lets begin to build salad
			System.out.println(ConsoleManagerConstants.TYPE_VEGETABLE); // type the vegetable u want to add			

			enumerationVegetablesInSalad = consoleInputOutput.readString(); // read numbers of vegetables from console			

			for (int i = 0; i < enumerationVegetablesInSalad.length(); i++) {
				switch (enumerationVegetablesInSalad.charAt(i)) {
				case ConsoleManagerConstants.PEPPER_NUMBER: // pepper
					
					System.out.println(String.format("\t%s: ", ConsoleManagerConstants.PEPPER));
					creaateSolanaceae(ConsoleManagerConstants.PEPPER, consoleInputOutput, saladBuilder);		
									
					break;
				case ConsoleManagerConstants.TOMATO_NUMBER: // tomato

					System.out.println(String.format("\t%s: ", ConsoleManagerConstants.TOMATO));					
					creaateSolanaceae(ConsoleManagerConstants.TOMATO, consoleInputOutput, saladBuilder);
					
					break;
				case ConsoleManagerConstants.SQUASH_NUMBER: // squash
					
					System.out.println(String.format("\t%s: ", ConsoleManagerConstants.SQUASH));

					majorParameters = readMajorParameters(consoleInputOutput);

					saladBuilder.getSalad().getVegetablesSalad().add(saladBuilder.createCucurbitaceae(ConsoleManagerConstants.SQUASH, 
							majorParameters.get(0), 
							majorParameters.get(1)));
					break;
				default:
					System.out.println(String.format("%s: %c", ConsoleManagerConstants.NO_SUCH_VEGETABLE_MESSAGE, enumerationVegetablesInSalad.charAt(i)));	
					break;							
				}
			}

			// Read information about oil that will add to salad.
			System.out.println(ConsoleManagerConstants.CHOOSE_OIL); // type the oil
			kindOfOil = consoleInputOutput.readString().toUpperCase();
			Oil oil = saladBuilder.createOil(kindOfOil);
			saladBuilder.getSalad().setOil(oil);

			// Print all info about salad.
			ViewInfo.printInfo(saladBuilder.getSalad());

			System.out.println(ConsoleManagerConstants.BUILD_ONE_MORE_SALAD); // do u want to build one more salad?			
			isBuilding = (consoleInputOutput.readChar() == ConsoleManagerConstants.NO_CHAR) ? true : false; 

		} while (isBuilding == false);
	}
	
	/**
	 * Create solanaceae, read need arguments for it.
	 * @param vegetableName - name of vegetable
	 * @param consoleInputOutput - console input-output streams object
	 * @param saladBuilder - salad builder
	 */
	private void creaateSolanaceae(String vegetableName, ConsoleInputOutput consoleInputOutput, SaladBuilder saladBuilder) {
		ArrayList<Double> majorParameters = readMajorParameters(consoleInputOutput);
		RipenessOfSolanaceae ripenessOfSolanaceae = readRipenessOfSolanaceae(consoleInputOutput);
		boolean isPulp = readIsPulp(consoleInputOutput);

		// Add new vegetable in the salad.
		saladBuilder.getSalad().getVegetablesSalad().add(saladBuilder.createSolanaceae(vegetableName, 
																					   majorParameters.get(0), 
																					   majorParameters.get(1), 
																					   ripenessOfSolanaceae, 
																					   isPulp));					
	}
	
	/**
	 * Read from console major parameters (inherent in each vegetable).
	 * First parameter (0) - weight of vegetable.
	 * Second parameter (1) - calories in 100 grams of vegetable. 
	 * @param consoleInputOutput object of class with input-output streams.
	 * @return set of the major parameters
	 */
	private ArrayList<Double> readMajorParameters(ConsoleInputOutput consoleInputOutput) {
		
		ArrayList<Double> majorParameters = new ArrayList<>();
		System.out.println(ConsoleManagerConstants.TYPE_WEIGHT_OF_VEGETABLE);
		majorParameters.add((double) consoleInputOutput.readDouble()); // weight
		
		System.out.println(ConsoleManagerConstants.TYPE_CALORIES_ONE_HUNDRED_GRAMS);
		majorParameters.add((double) consoleInputOutput.readDouble()); // calories
		
		return majorParameters;
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private RipenessOfSolanaceae readRipenessOfSolanaceae(ConsoleInputOutput consoleInputOutput) {
		System.out.println(ConsoleManagerConstants.TYPE_RIPENESS);
		String ripeness = consoleInputOutput.readString().toUpperCase();
		return RipenessOfSolanaceae.contains(ripeness) ? RipenessOfSolanaceae.valueOf(ripeness) : RipenessOfSolanaceae.BAD ;
	}
	
	/**
	 * 
	 * @param consoleInputOutput
	 * @return
	 */
	private boolean readIsPulp(ConsoleInputOutput consoleInputOutput) {
		System.out.println(ConsoleManagerConstants.TYPE_ABILITY_OF_PULP);
		char ability;
		do {
			ability = consoleInputOutput.readChar();
		} while (!((ability == ConsoleManagerConstants.NO_CHAR) || (ability == ConsoleManagerConstants.YES_CHAR)));
		
		return (ability == ConsoleManagerConstants.YES_CHAR) ? true : false;
	}	
}
