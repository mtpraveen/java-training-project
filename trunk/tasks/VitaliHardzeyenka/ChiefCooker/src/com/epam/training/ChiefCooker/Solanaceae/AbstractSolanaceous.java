package com.epam.training.ChiefCooker.Solanaceae;

import com.epam.training.ChiefCooker.Vegetable.*;

/**
 * @author EXUMLOKE
 * Abstract class that describe kind of vegetables call Solanaceous. 
 */
abstract public class AbstractSolanaceous extends AbstractVegetable {
	private RipenessOfSolanaceae ripeness; // vegetable ripeness degree from enumeration
	private boolean isPulp; // available of pulp [yes/no]
	
	/**
	 * Constructor.
	 */
	public AbstractSolanaceous() {
		super();
	}
	
	/**
	 * Constructor
	 * @param weightOfVegetable - weight of vegetable. Field from parent class AbstractVegetable.
	 * @param calorieInOneHundredGramms - calories in 100 grams. Field from parent class AbstractVegetable.
	 */
	public AbstractSolanaceous(double weightOfVegetable, double calorieInOneHundredGramms) {
		super(weightOfVegetable, calorieInOneHundredGramms);
	}
	
	/**
	 * Constructor.
	 * @param weightOfVegetable - weight of vegetable. Field from parent class AbstractVegetable.
	 * @param calorieInOneHundredGramms - calories in 100 grams. Field from parent class AbstractVegetable.
	 * @param ripeness - BAD/NORMAL/GOOD degree of ripeness. 
	 */
	public AbstractSolanaceous(double weightOfVegetable, double calorieInOneHundredGramms, RipenessOfSolanaceae ripeness) {
		super(weightOfVegetable, calorieInOneHundredGramms);
		setRipeness(ripeness);		
	}
	
	/**
	 * Set the private field ripeness. 
	 * @param ripeness - value from enumeration RipenessOfSolanaceae. 
	 */
	public void setRipeness(RipenessOfSolanaceae ripeness) {
		this.ripeness = ripeness;
	}
	
	/**
	 * @return value from enumeration RipenessOfSolanaceae. 
	 */
	public RipenessOfSolanaceae getRipeness() {
		return this.ripeness;
	}
	
	/**
	 * Set the private field availabilityOfPulp
	 * @param availabilityOfPulp
	 */
	public void setAvailabilityOfPulp(boolean isPulp) {
		this.isPulp = isPulp;
	}
	
	/**
	 * @return - boolean - ability of pulp.
	 */
	public boolean isPulp() {
		return this.isPulp;
	}	
}
