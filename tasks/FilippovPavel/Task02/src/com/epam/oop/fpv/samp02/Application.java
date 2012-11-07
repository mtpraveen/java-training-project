/**
 * 
 */
package com.epam.oop.fpv.samp02;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Pavel
 *
 */

public class Application {
	
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		
		//Create some objects candies
		Candy star = new Candy("Star", 15.0, 50.0, 5.0, 60, 10);
		Candy sun = new Candy("Sun", 8.5, 60.0, 7.5, 50, 10);
		Candy chups = new Candy("Chups", 15.0, 80.0, 10.0, 80, 10);
		
		FilledCandy halls = new FilledCandy("Halls", 12.5, 40.0, 4.5, 90, 10, 1.25);
		FilledCandy barberry = new FilledCandy("Barberry", 8.0, 55.5, 9.5, 60, 10, 0.75);
		
		Chocolate bittChoco = new Chocolate("BittChoco", 100.0, 300.0, 50.0, 60, 2, 40.0, 65);
		Chocolate milkChoco = new Chocolate("MilkChoco", 150.0, 350.0, 60.0, 60, 2, 0.0, 30);
		
		Cookie bravo = new Cookie("Bravo", 200.0, 200.0, 20.5, 90, 3, 6);
		Cookie teaBisc = new Cookie("TeaBisc", 150.0, 170.5, 15.0, 120, 3, 8);
		
		
		//Create a New Year kid gift
		Gift newYearGift = new Gift();
		
		newYearGift.addCandy(star);
		newYearGift.addCandy(sun);
		newYearGift.addCandy(chups);
		newYearGift.addCandy(halls);
		newYearGift.addCandy(barberry);
		newYearGift.addCandy(bittChoco);
		newYearGift.addCandy(milkChoco);
		newYearGift.addCandy(bravo);
		newYearGift.addCandy(teaBisc);
		
		System.out.println("The New Year gift consists of: ");
		System.out.println(newYearGift.toString());
		System.out.println();
		
		
		//Define a weight of a New Year kid gift
		System.out.print("The weight of a New Year kid gift is - ");
		System.out.println(newYearGift.getGiftWeight() + " gr.");
		System.out.println();
		
		
		//Sorting a gift by sugar amounting in candies
		System.out.println("Candies in a New Year kid gift sorted by sugar amounting:");
		newYearGift.sortBySugarAmount();
		for(int i=0; i<newYearGift.getCandies().size(); i++){
			System.out.println("Sugar of " + newYearGift.getCandy(i).getName() + " - " + newYearGift.getCandy(i).getSugar() + " gr.");
		}
		System.out.println();
		
		
		//Find the sweetest candy. Its sugar per gram must be a maximum value
		Candy maxSugPerGrmCandy = newYearGift.getSugarPerGramm();
		System.out.println("The sweetest candy is: " + maxSugPerGrmCandy.getName());
		System.out.println("Its sugar per gramm is - " + maxSugPerGrmCandy.getSugarPerGram() + " gr.");
		System.out.println();
		
		
		//Search candies by energy value and shelf life
		double ener1 = 50, ener2 = 100;
		int shelf1 = 40, shelf2 = 70;
		List<Candy> candies = new ArrayList<Candy>();
		candies = newYearGift.findEnerValShelfLifeCandies(ener1, ener2, shelf1, shelf2);
		Iterator<Candy> iter = candies.iterator();
		System.out.println("Energy value interval " + ener1 + " - " + ener2 + " gr. Shelf life interval " + shelf1 + " - " + shelf2 + " days");
		while(iter.hasNext()){
			Candy currCandy = iter.next();
			System.out.println(currCandy.getName() + " - " + currCandy.getCalory() + " gr." + " - " + currCandy.getShelfLife() + " days");
		}
	}
}
