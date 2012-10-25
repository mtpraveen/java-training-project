/**
 * 
 */
package com.epam.carPark;

/**
 * @author epam0010
 * 
 */
public final class Mini extends Car {
	final private String type = "Mini";
	
	Mini(){
		super();
	}
	
	Mini(String name, int cost, float consumption) {
		super(name, cost, consumption);
	}

	public String getType() {
		return type;
	}
}
