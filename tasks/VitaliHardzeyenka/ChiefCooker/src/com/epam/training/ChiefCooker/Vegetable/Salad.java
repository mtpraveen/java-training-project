package com.epam.training.ChiefCooker.Vegetable;

import java.util.ArrayList;

import com.epam.training.ChiefCooker.Oil.*;

/**
 * @author EXUMLOKE
 * This class contain information about salad as vegetables in it and kind of oil.
 */
final public class Salad {
	private ArrayList<AbstractVegetable> saladOfVegetables = new ArrayList<AbstractVegetable>(); // the list of vegetables in the salad
	private Oil oil; // the oil from enumeration KindsOfOil
	
	/**
	 * Constructor.
	 */
	public Salad() {}
	
	/**
	 * Constructor.
	 * @param oil - oil from enumeration KindsOfOil.
	 * @param vegetables - list of vegetables the will add in the salad.
	 */
	public Salad(Oil oil, ArrayList<AbstractVegetable>  vegetables) {
		setVegetablesToSalad(vegetables);
		setOil(oil);
	}
	
	public Salad(Oil oil, AbstractVegetable ... vegetables) {
		for (AbstractVegetable vegetable : vegetables) {
			getVegetablesSalad().add(vegetable);
		}
		
		setOil(oil);
	}
	
	
	/**
	 * Set the private field.
	 * @param vegetables saladOfVegetables
	 */
	public void setVegetablesToSalad(ArrayList<AbstractVegetable>  vegetables){
		this.saladOfVegetables.addAll(vegetables);
	}
	
	/**
	 * @return 
	 */
	public ArrayList<AbstractVegetable> getVegetablesSalad(){
		return this.saladOfVegetables;
	}
	
	/**
	 * Set the private field oil.
	 * @param oil
	 */
	public void setOil(Oil oil){
		this.oil = oil;
	}
	
	/**
	 * @return oil - field of enumeration KindOfOil.
	 */
	public Oil getOil(){
		return this.oil;
	}
}
