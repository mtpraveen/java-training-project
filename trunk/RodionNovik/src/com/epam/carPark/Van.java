/**
 * 
 */
package com.epam.carPark;

/**
 * @author epam0010
 *
 */
public final class Van extends Car {
	final private String type = "Van";
	
	Van(){
		super();
	}
	
	Van(String name, int cost, float consumption) {
		super(name, cost, consumption);
	}

	public String getType() {
		return type;
	}	
}