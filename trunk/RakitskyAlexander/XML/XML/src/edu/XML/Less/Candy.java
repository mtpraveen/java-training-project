/**
 * 
 */
package edu.XML.Less;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * @author epam0003
 *
 */
public class Candy {
	private String name;
	private int energy;
	private TypeCandies type;
	private Ingredients ingredients;
	private HashMap<ValueCandies, Integer> value= new HashMap<ValueCandies, Integer>();
	private String production;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the energy
	 */
	public int getEnergy() {
		return energy;
	}
	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	/**
	 * @return the type
	 */
	public TypeCandies getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TypeCandies type) {
		this.type = type;
	}
	/**
	 * @return the ingredients
	 */
	public Ingredients getIngredients() {
		return ingredients;
	}
	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(Ingredients ingredients) {
		this.ingredients = ingredients;
	}
	/**
	 * @return the value
	 */
	public HashMap<ValueCandies, Integer> getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(HashMap<ValueCandies, Integer> value) {
		this.value = value;
	}
	/**
	 * @return the production
	 */
	public String getProduction() {
		return production;
	}
	/**
	 * @param production the production to set
	 */
	public void setProduction(String production) {
		this.production = production;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Candy [name=" + name + ", energy=" + energy + ", type=" + type
				+ ", ingredients=" + ingredients + ", value=" + value
				+ ", production=" + production + "]";
	}
	/**
	 * @param name
	 * @param energy
	 * @param type
	 * @param ingredients
	 * @param value
	 * @param production
	 */
	public Candy(String name, int energy, TypeCandies type,
			Ingredients ingredients, HashMap<ValueCandies, Integer> value,
			String production) {
		super();
		this.name = name;
		this.energy = energy;
		this.type = type;
		this.ingredients = ingredients;
		this.value = value;
		this.production = production;
	}
	
	/**
	 * 
	 */
	public Candy() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
