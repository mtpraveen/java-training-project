package com.epam.training.ChiefCooker.Cucurbitaceae;

import com.epam.training.ChiefCooker.Constants.CucurbitaceaeConstants;

/**
 * @author EXUMLOKE
 * Class describe the squash.
 */
public class Squash extends AbstractCucurbitaceae implements ICooking {
	private double liquid; // weight of liquid in the squash
	
	/**
	 * Constructor.
	 */
	public Squash(){
		super();
		setLiquid();
		fry();		
	}
	
	/**
	 * Constructor.
	 * @param weightOfVegetable
	 * @param calorieInOneHundredGramms
	 */
	public Squash(double weightOfVegetable, double calorieInOneHundredGramms) {
		super(weightOfVegetable, calorieInOneHundredGramms);
		setLiquid();
		fry();		
	}
	
	/**
	 * Calculate and set the weight of liquid.s
	 */
	private void setLiquid() {
		this.liquid = (double)((double)super.getWeightOfVegetable() * CucurbitaceaeConstants.COEFFICIENT_LIQUID);
	}
	
	private double getLiquid() {
		return this.liquid;
	}
	
	/**
	 * Its need first to fry squash before add it to the salad.
	 */
	@Override
	public void fry() {		
		super.removePeel();
		boiloff();
	}
	
	/**
	 * When squash frying it lose liquid and mass with it.
	 * Calculate and set the new weight of vegetable after frying.
	 */
	@Override
	public void boiloff() {
		super.setWeightOfVegetable(super.getWeightOfVegetable() - getLiquid());
	}
}
