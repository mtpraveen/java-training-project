package com.epam.training.ChiefCooker.Builder;


import com.epam.training.ChiefCooker.Factory.CucurbitaceaeFactory;
import com.epam.training.ChiefCooker.Factory.SolanaceaeFactory;
import com.epam.training.ChiefCooker.Oil.KindsOfOil;
import com.epam.training.ChiefCooker.Oil.Oil;
import com.epam.training.ChiefCooker.Solanaceae.RipenessOfSolanaceae;
import com.epam.training.ChiefCooker.Vegetable.AbstractVegetable;
import com.epam.training.ChiefCooker.Vegetable.Salad;

/**
 * @author Gordeenko
 * Build salad with vegetables and oil. 
 */
public class SaladBuilder {
	
	private Salad salad = new Salad();
	
	public Salad getSalad() {
		return this.salad;
	}	
	
	/**
	 * Read information about oil that will add to salad.
	 * @param consoleInputOutput
	 * @return - kind of oil
	 */
	public Oil createOil(String kindOfOil) {		
		return KindsOfOil.contains(kindOfOil) ? (new Oil(KindsOfOil.valueOf(kindOfOil))) : null;
	}
	
	/**
	 * Create the solanaceae object.
	 * @param vegetableName - name of the vegetable: tomato or pepper
	 * @param consoleInputOutput - object of console input class
	 * @return created tomato or pepper
	 */
	public AbstractVegetable createSolanaceae(String vegetableName, 
											  double weight, 
											  double calorieInOneHundredGrams, 
											  RipenessOfSolanaceae ripenessOfSolanaceae, 
											  boolean isPulp) {
		 
		return SolanaceaeFactory.createSolanaceae(vegetableName, weight, calorieInOneHundredGrams, ripenessOfSolanaceae, isPulp);
	}
	
	/**
	 * Create the cucurbitaceae object.
	 * @param vegetableName name of the vegetable: squash
	 * @param consoleInputOutput
	 * @return return squash
	 */
	public AbstractVegetable createCucurbitaceae(String vegetableName, double weight, double calorieInOneHundredGrams) {
		return CucurbitaceaeFactory.createCucurbitaceae(vegetableName, weight, calorieInOneHundredGrams);
	}
}