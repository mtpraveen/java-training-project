package com.epam.carPark;
/**
 * @author epam0010
 *
 */
public abstract class Car {
	private String name;
	private int cost;
	private float consumption;
	

	Car(){
		this.name="No Name";
		this.cost=0;
		this.consumption=0.0F;
	}
	Car(String name,int cost,float consumption){
		this.name=name;
		this.cost=cost;
		this.consumption=consumption;
	}

	
	public abstract String getType();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public float getConsumption() {
		return consumption;
	}

	public void setConsumption(float consumption) {
		this.consumption = consumption;
	}
	
}