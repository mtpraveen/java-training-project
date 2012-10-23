package com.epam.training.ChiefCooker.Solanaceae;

import com.epam.training.ChiefCooker.Constants.SolenaceaeConstants;

/**
 * @author EXUMLOKE
 * Class describe pepper vegetable and consist all its properties.
 */
public class Pepper extends AbstractSolanaceous {
	private double weightOfSeeds;
	
	/**
	 * Constructor.
	 */
	public Pepper() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param weightOfVegetable - weight of vegetable. Field from parent class AbstractVegetable.
	 * @param calorieInOneHundredGramms - calories in 100 grams. Field from parent class AbstractVegetable.
	 * @param ripenness - ripeness of vegetable. Field from parent class AbstractSolanaceous.
	 * @param availabilityOfPulp - pulp of vegetable. Field from parent class AbstractSolanaceous.
	 */
	public Pepper(double weightOfVegetable, double calorieInOneHundredGramms, RipenessOfSolanaceae ripenness, boolean availabilityOfPulp) {
		super(weightOfVegetable, calorieInOneHundredGramms, ripenness);
		super.setAvailabilityOfPulp(availabilityOfPulp);
		cleanOfSeeds(ripenness); // clean seeds subject to ripeness of vegetable
	}	
	
	/**
	 * Clean seeds subject to ripeness of vegetable. For every degree of ripeness exist appropriate mass seeds in vegetable. 
	 * @param ripenness - ripeness of vegetable, value of RipenessOfSolanaceae enumeration. 
	 */
	private void cleanOfSeeds(RipenessOfSolanaceae ripenness) {
		if (super.getAvailabilityOfPulp() == true){
			setWeightOfSeeds(ripenness); // set weight of seed subject to ripeness
			super.setWeightOfVegetable((double)(super.getWeightOfVegetable() - getWeightOfSoads())); // calculate mass of vegetable without seeds
			super.setAvailabilityOfPulp(false); // there is no pulp (seeds) after cleaningS
		} else {
			super.setAvailabilityOfPulp(true);
		}
	}
	
	/**
	 * Calculate the coefficient of seeds subject to ripeness
	 * @param ripenness
	 */
	public void setWeightOfSeeds(RipenessOfSolanaceae ripenness) {
		if (ripenness instanceof RipenessOfSolanaceae) {
			this.weightOfSeeds = (super.getWeightOfVegetable()) * SolenaceaeConstants.COEFFICIENT_WEIGHT_SEEDS_NORMAL_GOOD;			
		} else {
			this.weightOfSeeds = (super.getWeightOfVegetable()) * SolenaceaeConstants.COEFFICIENT_WEIGHT_SEEDS_BAD;
		}
	}
	
	public double getWeightOfSoads(){
		return this.weightOfSeeds;
	}
}
