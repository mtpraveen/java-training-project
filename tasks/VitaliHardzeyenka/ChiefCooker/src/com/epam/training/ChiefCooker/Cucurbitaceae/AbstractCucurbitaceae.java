package com.epam.training.ChiefCooker.Cucurbitaceae;

import com.epam.training.ChiefCooker.Constants.CucurbitaceaeConstants;
import com.epam.training.ChiefCooker.Vegetable.*;

/**
 * @author EXUMLOKE
 * Class describe the type of vegetable cucurbitaceae.
 */
abstract public class AbstractCucurbitaceae extends AbstractVegetable {
	private double peel; // weight of peel in vegetable
	
	/**
	 * Constructor.
	 */
	public AbstractCucurbitaceae() {
		super();
		setPeel();
	}
	
	/**
	 * Constructor.
	 * @param weightOfVegetable - weight of vegetable. Field from the parent class.
	 * @param calorieInOneHundredGramms - field form the parent class.
	 */
	public AbstractCucurbitaceae(double weightOfVegetable, double calorieInOneHundredGramms) {
		super(weightOfVegetable, calorieInOneHundredGramms);
	}
	
	/**
	 * Calculate the weight of peel.
	 */
	private void setPeel() {
		this.peel = super.getWeightOfVegetable() * CucurbitaceaeConstants.COEFFICIENT_WEIGHT_OF_PEEL;
	}
	
	/**
	 * Remove (weight of) peel from the vegetable and set new weight. 
	 */
	public void removePeel() {
		super.setWeightOfVegetable((double)(super.getWeightOfVegetable()  - this.peel));
	}
}