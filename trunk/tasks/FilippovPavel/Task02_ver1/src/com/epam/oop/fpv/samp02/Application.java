/**
 * 
 */
package com.epam.oop.fpv.samp02;

import java.io.*;

/**
 * @author Pavel
 *
 */


/*
 * Class Candy - class describes a simple candies
 * Basic properties: weight, calory, sugar, shelfLife, count
 */
 class Candy{
	String name;
	private double weight, calory, sugar;
	private int shelfLife, count;
	
	Candy(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount){
		name = cName;
		weight = cWght;
		calory = cCalory;
		sugar = cSugar;
		shelfLife = cShLife;
		count = cCount;
	}
	
	public String getName() {
		return name;
	}
	public double getWeight() {
		return weight;
	}
	public double getCalory() {
		return calory;
	}
	public double getSugar() {
		return sugar;
	}
	public int getShelfLife() {
		return shelfLife;
	}
	public int getCount() {
		return count;
	}
}

 
 /*
  * Class FilledCandy - class describes a filled candies
  * extends the class Candy
  * Added property: fillVol - filling the candies
  */
class FilledCandy extends Candy{
	private double fillVol;
	
	FilledCandy(String fName, double fWght, double fCalory, double fSugar, int fShLife, int fCount, double fFill){ 
		super(fName, fWght, fCalory, fSugar, fShLife, fCount);
		fillVol = fFill;
	}
}
	


/*
 * Class Chocolate - class describes a chocolates
 * extends the class FilledCandy
 * Added property: cocoaPerc - percentage of cocoa
 */
class Chocolate extends FilledCandy{
	private int cocoaPerc;

	Chocolate(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount, double cFill, int cCocoa){ 
		super(cName, cWght, cCalory, cSugar, cShLife, cCount, cFill);
		if (cCocoa<1 && cCocoa>99){
			System.out.println("Cocoa percent must be in 1 - 99 interval");
			cocoaPerc = 1;
		}
		else{
			cocoaPerc = cCocoa;
		}
	}
}


/*
 * Class Cookie - class describes a cookies
 * extends the class Candy
 * Added property: countInPack  - the count of cookies in a pack
 */
class Cookie extends Candy{
	private int countInPack;

	Cookie(String cName, double cWght, double cCalory, double cSugar, int cShLife, int cCount, int cCountPack){ 
		super(cName, cWght, cCalory, cSugar, cShLife, cCount);
		countInPack = cCountPack;
	}
}
	

/*
 * Class Gift - class describes a gift, contains references to classes of candies
 * Basic methods:
 * 1. methods of determining the weight of a gift
 * 2. methods of determining the candy with the highest sugar content per gram
 * 3. sorting methods on the count of sugar candy
 * 4. search methods sweets satisfy a range of parameters
 */
class Gift{
	public Candy[] candies;
	public FilledCandy[] filledCandies;
	public Chocolate[] chocolates;
	public Cookie[] cookies;
	private double giftWeight;
	private Candy[] tmpCandy;
	public String nameMostSugar;
	public double mostSugar;
	
	Gift(Candy[] gCandy, FilledCandy[] gFilledCandy, Chocolate[] gChocolate, Cookie[] gCookie){
		candies = new Candy[gCandy.length];
		filledCandies = new FilledCandy[gFilledCandy.length];
		chocolates = new Chocolate[gChocolate.length];
		cookies = new Cookie[gCookie.length];
		candies = gCandy;
		filledCandies = gFilledCandy;
		chocolates = gChocolate;
		cookies = gCookie;
		giftWeight = 0.0;
		nameMostSugar = "";
		mostSugar = 0.0;
	}
	
	
	//two methods for determining the weight of a gift
	private double getGiftW(Candy[] tmpArr){
		double tmpWght = 0.0;
		for (int i = 0; i<tmpArr.length; i++)
			tmpWght += tmpArr[i].getCount() * tmpArr[i].getWeight();
		return tmpWght;
	}
	public double getGiftWeight(){
		tmpCandy = candies;
		giftWeight += getGiftW(tmpCandy);
		tmpCandy = filledCandies;
		giftWeight += getGiftW(tmpCandy);
		tmpCandy = chocolates;
		giftWeight += getGiftW(tmpCandy);
		tmpCandy = cookies;
		giftWeight += getGiftW(tmpCandy);
		return giftWeight;
	}
	
	
	//two methods for determining the candy with the highest sugar content per gram
	private void getMostS(Candy[] tmpArr){
		double sugPerGrm = 0.0;
		for (int i = 0; i<tmpArr.length; i++){
			sugPerGrm = tmpArr[i].getSugar() / tmpArr[i].getWeight();
			if (sugPerGrm > mostSugar){
				mostSugar = sugPerGrm;
				nameMostSugar = tmpArr[i].getName();
			}
		}
	}
	public String getMostSugar(){
		tmpCandy = candies;
		getMostS(tmpCandy);
		tmpCandy = filledCandies;
		getMostS(tmpCandy);
		tmpCandy = chocolates;
		getMostS(tmpCandy);
		tmpCandy = cookies;
		getMostS(tmpCandy);
		return nameMostSugar;
	}
	
	//two sorting methods on the count of sugar candy
	private void sortByS(Candy[] tmpArr){
		Candy tmpCandy;
		for (int i = 0; i<tmpArr.length-1; i++){
			for (int j = 0; j<tmpArr.length-i-1; j++){
				if (tmpArr[j].getSugar()>tmpArr[j+1].getSugar()){
					tmpCandy = tmpArr[j];
					tmpArr[j] = tmpArr[j+1];
					tmpArr[j+1] = tmpCandy;
				}
			}
		}
	}
	public void sortBySugar(){
		tmpCandy = null;
		tmpCandy = new Candy[candies.length+filledCandies.length+chocolates.length+cookies.length];
		//System.arraycopy(tmpCandy, 0, filledCandies, 0, filledCandies.length);
		//System.arraycopy(tmpCandy, 0, chocolates, 0, chocolates.length);
		//System.arraycopy(tmpCandy, 0, filledCandies, 0, chocolates.length);
		for (int i = 0; i<candies.length; i++){
			tmpCandy[i] = candies[i];
		}
		for (int i = 0; i<filledCandies.length; i++){
			tmpCandy[candies.length+i] = filledCandies[i];
		}
		for (int i = 0; i<chocolates.length; i++){
			tmpCandy[candies.length+filledCandies.length+i] = chocolates[i];
		}
		for (int i = 0; i<cookies.length; i++){
			tmpCandy[candies.length+filledCandies.length+chocolates.length+i] = cookies[i];
		}	
		sortByS(tmpCandy);
		System.out.println("Candies sorting by sugar count:");
		for (int i = 0; i<tmpCandy.length; i++)
			System.out.println(tmpCandy[i].getName() + " - " + tmpCandy[i].getSugar() + "g.");	
	}
	
	
	//two searching methods sweets satisfy a range of parameters
	private void searchES(Candy[] tmpArr, double ener1, double ener2, int shelf1, int shelf2){
		for (int i = 0; i<tmpArr.length; i++){
			if (tmpArr[i].getCalory() >= ener1 && tmpArr[i].getCalory() <= ener2)
				if (tmpArr[i].getShelfLife() >= shelf1 && tmpArr[i].getShelfLife() <= shelf2)
					System.out.println(tmpArr[i].getName() + " - " + tmpArr[i].getCalory() + "g." + " - " + tmpArr[i].getShelfLife() + " days");
		}
	}
	public void searchEnerShelf(double ener1, double ener2, int shelf1, int shelf2){
		System.out.println("Energy value interval " + ener1 + " - " + ener2 + " Shelf life interval " + shelf1 + " - " + shelf2 + " days");
		tmpCandy = candies;
		searchES(tmpCandy, ener1, ener2, shelf1, shelf2);
		tmpCandy = filledCandies;
		searchES(tmpCandy, ener1, ener2, shelf1, shelf2);
		tmpCandy = chocolates;
		searchES(tmpCandy, ener1, ener2, shelf1, shelf2);
		tmpCandy = cookies;
		searchES(tmpCandy, ener1, ener2, shelf1, shelf2);
	}
}



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
