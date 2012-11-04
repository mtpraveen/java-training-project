/**
 * 
 */
package com.epam.cars;

/**
 * @author epam0010
 * 
 */
public final class Mini extends Car {
	final private String type = "Mini";
	
	public Mini(){
		super();
	}
	
	Mini(String name, int cost, float consumption) {
		super(name, cost, consumption);
	}

	public String getType() {
		return type;
	}
}
