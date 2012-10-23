package com.epam.training.ChiefCooker.Solanaceae;

import com.epam.training.ChiefCooker.Constants.SolenaceaeConstants;

/**
 * @author EXUMLOKE
 * That class define the tomato vegetable and consist it properties.
 */
public class Tomato extends AbstractSolanaceous {
	private double weightOfPulp; // major weight of vegetable. It can decreasing when vegetable is frying
	
	/**
	 * Constructor.
	 */
	public Tomato(){
		super();
	}
	
	/**
	 * Constructor.
	 * @param weightOfVegetable - weight of vegetable. Field from parent class AbstractVegetable.
	 * @param calorieInOneHundredGramms - calories in 100 grams. Field from parent class AbstractVegetable.
	 * @param ripenness - ripeness of vegetable. Field from parent class AbstractSolanaceous.
	 * @param availabilityOfPulp - pulp of vegetable. Field from parent class AbstractSolanaceous.
	 */
	public Tomato(double weightOfVegetable, double calorieInOneHundredGramms, RipenessOfSolanaceae ripeness, boolean availabilityOfPulp){
		super(weightOfVegetable, calorieInOneHundredGramms, ripeness);
		super.setAvailabilityOfPulp(availabilityOfPulp);
		removePulp(ripeness); // remove the pulp from the vegetable subject to the ripeness degree
	}
	
	/**
	 * Remove the pulp from the vegetable subject to the ripeness degree.
	 * @param ripeness
	 */
	private void removePulp(RipenessOfSolanaceae ripeness) {
		if (super.getAvailabilityOfPulp() == true) { // if the vegetable has the pulp
			setWeightOfPulp(ripeness); // set weight of pulp subject to ripeness degree
			super.setWeightOfVegetable(super.getWeightOfVegetable() - getWeightOfPulp()); // worse ripeness - more pulp will remove
			super.setAvailabilityOfPulp(false); // there is no pulp in vegetable after removing :)
		} else {
			super.setAvailabilityOfPulp(true);
		}
	}
	
	/**
	 * Set the weight of pulp subject to ripeness degree. That worse ripeness of vegetable - than more pulp will be remove. 
	 * @param ripeness - degree of ripeness, value from enumeration RipenessOfSolanaceae 
	 */
	public void setWeightOfPulp(RipenessOfSolanaceae ripeness)
	{
		// That better ripeness of vegetable than more pulp in it. 
		double coeffitientWeightOfPulp = SolenaceaeConstants.COEFFICIENT_WEIGHT_PULP_BAD; // initialize variable with one of the variants
		
		switch (this.getRipeness()) { // calculate coefficient of pulp weight 
			case BAD: 
				coeffitientWeightOfPulp = SolenaceaeConstants.COEFFICIENT_WEIGHT_PULP_BAD;				
				break;
			case NORMAL:
				coeffitientWeightOfPulp = SolenaceaeConstants.COEFFICIENT_WEIGHT_PULP_NORMAL;	
				break;
			case GOOD:
				coeffitientWeightOfPulp = SolenaceaeConstants.COEFFICIENT_WEIGHT_PULP_GOOD;
				break;
			default:
				// error				
		}
		
		this.weightOfPulp = super.getWeightOfVegetable() * coeffitientWeightOfPulp; // calculate the weight of pulp
	}
	
	/**
	 * @return weightOfPulp
	 */
	public double getWeightOfPulp()
	{
		return this.weightOfPulp;
	}
}
