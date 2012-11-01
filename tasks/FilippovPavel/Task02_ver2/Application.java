/**
 * 
 */
package com.epam.oop.fpv.samp02;

import java.io.*;
import com.epam.oop.fpv.samp02.*;

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
		Candy Star = new Candy("Star", 15.0, 50.5, 5.0, 60, 10);
		Candy Sun = new Candy("Sun", 8.5, 60.0, 7.5, 50, 10);
		Candy Chups = new Candy("Chups", 15.0, 80.0, 10.0, 80, 10);
		
		FilledCandy Halls = new FilledCandy("Halls", 12.5, 40.0, 4.5, 90, 10, 1.25);
		FilledCandy Barberry = new FilledCandy("Barberry", 8.0, 55.5, 9.5, 60, 10, 0.75);
		
		Chocolate BittChoco = new Chocolate("BittChoco", 100.0, 300.0, 50.0, 60, 2, 40.0, 65);
		Chocolate MilkChoco = new Chocolate("MilkChoco", 150.0, 350.0, 60.0, 60, 2, 0.0, 30);
		
		Cookie Bravo = new Cookie("Bravo", 200.0, 200.0, 20.5, 90, 3, 6);
		Cookie TeaBisc = new Cookie("TeaBisc", 150.0, 170.5, 15.0, 120, 3, 8);
		
		//Children's gift with the definition of the weight
		Candy[] cndArr;
		cndArr = new Candy[] {Star, Sun, Chups};
		
		FilledCandy[] filledArr;
		filledArr = new FilledCandy[] {Halls, Barberry};
		
		Chocolate[] chocArr;
		chocArr = new Chocolate[] {BittChoco, MilkChoco};
		
		Cookie[] cookArr;
		cookArr = new Cookie[] {Bravo, TeaBisc};
		
		Gift NewYear = new Gift(cndArr, filledArr, chocArr, cookArr);
		System.out.println("New Year gift`s weight is " + NewYear.getGiftWeight()+"g.");
		System.out.println("");
		
		//Search the most sugar per gramms candy
		System.out.format("The most sugar per gramm candy is " +  NewYear.getMostSugar() + ". Its sugar per gram is %5.3f.%n", NewYear.mostSugar);
		System.out.println("");
		
		//Sorting by sugar counting in candies
		NewYear.sortBySugar();
		System.out.println("");
		
		//Search candies by energy value and shelf life
		NewYear.searchEnerShelf(50.0, 100.0, 40, 70);
	}
}
