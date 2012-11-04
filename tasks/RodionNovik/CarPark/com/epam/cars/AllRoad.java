/**
 * 
 */
package com.epam.cars;

/**
 * @author epam0010
 *
 */
public final class AllRoad extends Car {
	final private String type = "AllRoad";
	
	public AllRoad(){
		super();
	}
	
	AllRoad(String name, int cost, float consumption) {
		super(name, cost, consumption);
	}
	
	public String getType() {
		return type;
	}

}
