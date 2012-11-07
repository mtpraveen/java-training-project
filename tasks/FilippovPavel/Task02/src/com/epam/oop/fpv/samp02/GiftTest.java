/**
 * 
 */
package com.epam.oop.fpv.samp02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Pavel
 *
 */
/**
 * @author Pavel
 *
 */
public class GiftTest {

	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void candiesCreatingTest() {
		
		
		//Create some objects candies
		Candy star = new Candy("Star", 10, 50, 5, 100, 10);
		Candy sun = new Candy("Sun", 8.5, 60.0, 7.5, 50, 10);
		Candy chups = new Candy("Chups", 15.0, 80.0, 10.0, 80, 10);
		
		FilledCandy halls = new FilledCandy("Halls", 12.5, 40.0, 4.5, 90, 10, 1.25);
		FilledCandy barberry = new FilledCandy("Barberry", 8.0, 55.5, 9.5, 60, 10, 0.75);
		
		Chocolate bittChoco = new Chocolate("BittChoco", 100.0, 300.0, 50.0, 60, 2, 40.0, 65);
		Chocolate milkChoco = new Chocolate("MilkChoco", 150.0, 350.0, 60.0, 60, 2, 0.0, 30);
		
		Cookie bravo = new Cookie("Bravo", 200.0, 200.0, 20.5, 90, 3, 6);
		Cookie teaBisc = new Cookie("TeaBisc", 150.0, 170.5, 15.0, 120, 3, 8);
	
		
		//Test creating some objects
		assertEquals("Star", star.getName());   
		assertEquals(5, (int)(star.getSugarPerGram()*10));
		assertEquals(10, (int)star.getWeight());
		assertEquals(50, (int)star.getCalory());
		assertEquals(5, (int)star.getSugar());
		assertEquals(100, star.getShelfLife());
		assertEquals(10, star.getCount());
		
		
		//Test create a New Year kid gift
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
		
		assertEquals("Star", newYearGift.getCandy(0).getName());
		assertEquals("Sun", newYearGift.getCandy(1).getName());
		assertEquals("Chups", newYearGift.getCandy(2).getName());
		assertEquals("Halls", newYearGift.getCandy(3).getName());
		assertEquals("Barberry", newYearGift.getCandy(4).getName());
		assertEquals("BittChoco", newYearGift.getCandy(5).getName());
		assertEquals("MilkChoco", newYearGift.getCandy(6).getName());
		assertEquals("Bravo", newYearGift.getCandy(7).getName());
		assertEquals("TeaBisc", newYearGift.getCandy(8).getName());
		
		List<Candy> candies = new ArrayList<Candy>();
		candies = newYearGift.getCandies();
		assertEquals("Star", candies.get(0).getName());
		
		
		//Test calculating a weight of a gift
		assertEquals(2090,(int)newYearGift.getGiftWeight());
		
		
		//Test sorting of a sugar amounting in a gift
		newYearGift.sortBySugarAmount();
		assertEquals("Halls", newYearGift.getCandy(0).getName());
		assertEquals("Star", newYearGift.getCandy(1).getName());
		assertEquals("Sun", newYearGift.getCandy(2).getName());
		assertEquals("Barberry", newYearGift.getCandy(3).getName());
		assertEquals("Chups", newYearGift.getCandy(4).getName());
		assertEquals("TeaBisc", newYearGift.getCandy(5).getName());
		assertEquals("Bravo", newYearGift.getCandy(6).getName());
		assertEquals("BittChoco", newYearGift.getCandy(7).getName());
		assertEquals("MilkChoco", newYearGift.getCandy(8).getName());
		
		
		//Find the sweetest candy. Its sugar per gram must be a maximum value
		Candy maxSugPerGrmCandy = newYearGift.getSugarPerGramm();
		assertEquals("Barberry", maxSugPerGrmCandy.getName());
		
		
		//Test a searching candies by energy value and shelf life
		double ener1 = 50, ener2 = 100;
		int shelf1 = 40, shelf2 = 70;
		List<Candy> listOfCandies = new ArrayList<Candy>();
		listOfCandies = newYearGift.findEnerValShelfLifeCandies(ener1, ener2, shelf1, shelf2);
		assertEquals("Sun", listOfCandies.get(0).getName());
		assertEquals("Barberry", listOfCandies.get(1).getName());
		
	}
	
	
}
