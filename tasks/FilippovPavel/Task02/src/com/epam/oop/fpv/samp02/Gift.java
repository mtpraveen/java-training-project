/**
 * 
 */
package com.epam.oop.fpv.samp02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Pavel
 *
 */

/*
 * Class Gift - class describes a gift, contains a list of candies
 * Basic methods:
 * 1. method of determining the weight of a gift
 * 2. method of determining the candy with the highest sugar content per gram
 * 3. sorting method on the amount of sugar candy
 * 4. searching method sweets satisfy a range of parameters
 */
public class Gift{

	private List<Candy> candies;
	
	public Gift(){
		candies = new ArrayList<Candy>();
	}
	public void addCandy(Candy candy){
		candies.add(candy);         
	}
	public Candy getCandy(int ind){
		return candies.get(ind);
	}                  
	public List<Candy> getCandies(){
		return candies;
	}
	
	@Override
	public String toString() {
		String retString = "";
		for(Candy currCandy : this.candies)
			retString += currCandy.toString()+"\n";
		return retString;
	}
	
	//method of determining the weight of a gift
	public double getGiftWeight(){
		double giftWeight = 0.0;
		for(Candy currCandy : this.candies)
			giftWeight += currCandy.getCount()*currCandy.getWeight();
		return giftWeight;
	}
	
	//class comparator for sorting candies by sugar amount
	class SortBySugarAmount implements Comparator<Candy> {
		public int compare(Candy candy1, Candy candy2) {
			double sugar1 = candy1.getSugar(); 
			double sugar2 = candy2.getSugar();
			int result = (sugar1 > sugar2) ? 1: (sugar1 < sugar2) ? -1: 0;
			return result;
	       }
	}
	
	//sorting method on the amount of sugar candy
	public void sortBySugarAmount(){
			Collections.sort(this.candies, new SortBySugarAmount());
	}
	
	//method of determining the candy with the highest sugar content per gram
	public Candy getSugarPerGramm(){
		double sugPerGrm = 0.0;
		double maxSugar = 0.0;
		Candy retCandy = null;
		for(Candy currCandy : this.candies){
			sugPerGrm = 0.0;
			sugPerGrm = currCandy.getSugarPerGram();
			if (sugPerGrm > maxSugar){
				maxSugar = sugPerGrm;
				retCandy = currCandy;
			}	
		}
		return retCandy;
	}

	//Searching method of determining candies by energy value and shelf life
	public List<Candy> findEnerValShelfLifeCandies(double ener1, double ener2, int shelf1, int shelf2){
		List<Candy> retCandies = new ArrayList<Candy>();
		double energy = 0.0;
		int shelfLife = 0;
		for(Candy currCandy : this.candies){
			energy = currCandy.getCalory();
			shelfLife = currCandy.getShelfLife();
			if  (energy >= ener1 && energy <= ener2){
				if (shelfLife >= shelf1 && shelfLife <= shelf2){
					retCandies.add(currCandy); 
				}
			}
		}
		return retCandies;
	}

}

