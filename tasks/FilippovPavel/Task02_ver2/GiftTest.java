/**
 * 
 */
package com.epam.oop.fpv.samp02;

import static org.junit.Assert.*;

import org.junit.Test;

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
		Candy Star = new Candy("Star", 15.0, 50.5, 5.0, 60, 10);
		Candy Sun = new Candy("Sun", 8.5, 60.0, 7.5, 50, 10);
		Candy Chups = new Candy("Chups", 15.0, 80.0, 10.0, 80, 10);
		
		FilledCandy Halls = new FilledCandy("Halls", 12.5, 40.0, 4.5, 90, 10, 1.25);
		FilledCandy Barberry = new FilledCandy("Barberry", 8.0, 55.5, 9.5, 60, 10, 0.75);
		
		Chocolate BittChoco = new Chocolate("BittChoco", 100.0, 300.0, 50.0, 60, 2, 40.0, 65);
		Chocolate MilkChoco = new Chocolate("MilkChoco", 150.0, 350.0, 60.0, 60, 2, 0.0, 30);
		
		Cookie Bravo = new Cookie("Bravo", 200.0, 200.0, 20.5, 90, 3, 6);
		Cookie TeaBisc = new Cookie("TeaBisc", 150.0, 170.5, 15.0, 120, 3, 8);
	
		//Test creating some objects
		assertEquals("Star", Star.getName());   
		assertEquals(10, Halls.getCount());
		assertEquals(30, MilkChoco.getCocoaPerc());
	
		
		
		//Create a gift
		Candy[] cndArr;
		cndArr = new Candy[] {Star, Sun, Chups};
		
		FilledCandy[] filledArr;
		filledArr = new FilledCandy[] {Halls, Barberry};
		
		Chocolate[] chocArr;
		chocArr = new Chocolate[] {BittChoco, MilkChoco};
		
		Cookie[] cookArr;
		cookArr = new Cookie[] {Bravo, TeaBisc};
		
		Gift NewYear = new Gift(cndArr, filledArr, chocArr, cookArr);
		
		//Test creating a gift and calculating its weight
		assertEquals(2140, (int)NewYear.getGiftWeight());  
		
		
		
		//Test searching most sugar per gramms candy
		assertEquals("Barberry", NewYear.getMostSugar());
		assertEquals(1187, (int)(NewYear.mostSugar*1000));
		
	}
}
