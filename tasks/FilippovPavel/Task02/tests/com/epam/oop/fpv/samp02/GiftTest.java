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

public class GiftTest {
	
	private static final String STAR = "Star";  
	private static final String SUN = "Sun";  
	private static final String CHUPS = "Chups";  
	private static final String HALLS = "Halls";  
	private static final String BARBERRY = "Barberry";  
	private static final String BITTCHOCO = "BittChoco";  
	private static final String MILKCHOCO = "MilkChoco";  
	private static final String BRAVO = "Bravo";  
	private static final String TEABISC = "TeaBisc";  
	public static Gift newYearGift = new Gift();  

	
	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
    @Test 
	public void giftCreating() {

		//Create a New Year kid gift
		newYearGift.addCandy(new Candy(STAR, 10, 50, 5, 100, 10));
		newYearGift.addCandy(new Candy(SUN, 8.5, 60.0, 7.5, 50, 10));
		newYearGift.addCandy(new Candy(CHUPS, 15.0, 80.0, 10.0, 80, 10));
		newYearGift.addCandy(new FilledCandy(HALLS, 12.5, 40.0, 4.5, 90, 10, 1.25));
		newYearGift.addCandy(new FilledCandy(BARBERRY, 8.0, 55.5, 9.5, 60, 10, 0.75));
		newYearGift.addCandy(new Chocolate(BITTCHOCO, 100.0, 300.0, 50.0, 60, 2, 40.0, 65));
		newYearGift.addCandy(new Chocolate(MILKCHOCO, 150.0, 350.0, 60.0, 60, 2, 0.0, 30));
		newYearGift.addCandy(new Cookie(BRAVO, 200.0, 200.0, 20.5, 90, 3, 6));
		newYearGift.addCandy(new Cookie(TEABISC, 150.0, 170.5, 15.0, 120, 3, 8));
    }
    
    
	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
    @Test
	public void giftCreatingTest() {
    	
    	//Test create a New Year kid gift
		assertEquals(STAR, newYearGift.getCandy(0).getName());
		assertEquals(SUN, newYearGift.getCandy(1).getName());
		assertEquals(CHUPS, newYearGift.getCandy(2).getName());
		assertEquals(HALLS, newYearGift.getCandy(3).getName());
		assertEquals(BARBERRY, newYearGift.getCandy(4).getName());
		assertEquals(BITTCHOCO, newYearGift.getCandy(5).getName());
		assertEquals(MILKCHOCO, newYearGift.getCandy(6).getName());
		assertEquals(BRAVO, newYearGift.getCandy(7).getName());
		assertEquals(TEABISC, newYearGift.getCandy(8).getName());	
		
	}

		
	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
    @Test
	public void weightGiftTest() {
		
		//Test calculating a weight of a gift
		assertEquals(2090,(int)newYearGift.getGiftWeight());
		
		newYearGift.getCandy(0).setWeight(20);
		assertEquals(2190,(int)newYearGift.getGiftWeight());
		
		newYearGift.getCandy(6).setWeight(140);
		assertEquals(2170,(int)newYearGift.getGiftWeight());
		
	}	
    
		
	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void sortGiftTest() {		
		
		//Test sorting of a sugar amounting in a gift
		newYearGift.sortBySugarAmount();
		
		assertEquals(HALLS, newYearGift.getCandy(0).getName());
		assertEquals(STAR, newYearGift.getCandy(1).getName());
		assertEquals(SUN, newYearGift.getCandy(2).getName());
		assertEquals(BARBERRY, newYearGift.getCandy(3).getName());
		assertEquals(CHUPS, newYearGift.getCandy(4).getName());
		assertEquals(TEABISC, newYearGift.getCandy(5).getName());
		assertEquals(BRAVO, newYearGift.getCandy(6).getName());
		assertEquals(BITTCHOCO, newYearGift.getCandy(7).getName());
		assertEquals(MILKCHOCO, newYearGift.getCandy(8).getName());
	
		
		newYearGift.getCandy(0).setSugar(20);
		newYearGift.getCandy(3).setSugar(12);
		newYearGift.getCandy(5).setSugar(40);
		newYearGift.getCandy(7).setSugar(5);
		
		newYearGift.sortBySugarAmount();

		assertEquals(STAR, newYearGift.getCandy(0).getName());
		assertEquals(BITTCHOCO, newYearGift.getCandy(1).getName());
		assertEquals(SUN, newYearGift.getCandy(2).getName());
		assertEquals(CHUPS, newYearGift.getCandy(3).getName());
		assertEquals(BARBERRY, newYearGift.getCandy(4).getName());
		assertEquals(HALLS, newYearGift.getCandy(5).getName());
		assertEquals(BRAVO, newYearGift.getCandy(6).getName());
		assertEquals(TEABISC, newYearGift.getCandy(7).getName());
		assertEquals(MILKCHOCO, newYearGift.getCandy(8).getName());
		
	}
	
		
	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void maxSugarAmountCandyTest() {	
		
		//Find the sweetest candy. Its sugar per gram must be a maximum value
		Candy maxSugPerGrmCandy = newYearGift.getSugarPerGramm();
		assertEquals(HALLS, maxSugPerGrmCandy.getName());
		
		newYearGift.getCandy(5).setSugar(5);
		newYearGift.getCandy(4).setSugar(5);
		newYearGift.getCandy(2).setSugar(34);
		maxSugPerGrmCandy = newYearGift.getSugarPerGramm();
		assertEquals(SUN, maxSugPerGrmCandy.getName());
	}
	
	
	/**
	 * Test method for {@link java.lang.Object#equals(java.lang.Object)}.
	 */
	@Test
	public void searchingCandiesByParamsTest() {	
	
		//Test a searching candies by energy value and shelf life
		double ener1 = 50, ener2 = 100;
		int shelf1 = 40, shelf2 = 70;
		List<Candy> listOfCandies = new ArrayList<Candy>();
		listOfCandies = newYearGift.findEnerValShelfLifeCandies(ener1, ener2, shelf1, shelf2);
		assertEquals(SUN, listOfCandies.get(0).getName());
		assertEquals(BARBERRY, listOfCandies.get(1).getName());
		
		newYearGift.getCandy(2).setCalory(150);
		listOfCandies = newYearGift.findEnerValShelfLifeCandies(ener1, ener2, shelf1, shelf2);
		assertEquals(BARBERRY, listOfCandies.get(0).getName());
	
	}
	
}
