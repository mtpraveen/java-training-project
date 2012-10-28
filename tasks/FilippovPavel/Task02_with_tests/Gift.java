/**
 * 
 */
package com.epam.oop.fpv.samp02;

/**
 * @author Pavel
 *
 */

/*
 * Class Gift - class describes a gift, contains references to classes of candies
 * Basic methods:
 * 1. methods of determining the weight of a gift
 * 2. methods of determining the candy with the highest sugar content per gram
 * 3. sorting methods on the count of sugar candy
 * 4. search methods sweets satisfy a range of parameters
 */
public class Gift{
	public Candy[] candies;
	public FilledCandy[] filledCandies;
	public Chocolate[] chocolates;
	public Cookie[] cookies;
	
	private double giftWeight;
	private Candy[] tmpCandy;
	
	public String nameMostSugar;
	public double mostSugar;
	
	Gift(Candy[] gCandy, FilledCandy[] gFilledCandy, Chocolate[] gChocolate, Cookie[] gCookie){
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
		int tmpLength = candies.length+filledCandies.length+chocolates.length+cookies.length;
		tmpCandy = new Candy[tmpLength];
		
		System.arraycopy(candies, 0, tmpCandy, 0, candies.length);
		System.arraycopy(filledCandies, 0, tmpCandy, candies.length, filledCandies.length);
		System.arraycopy(chocolates, 0, tmpCandy, candies.length+filledCandies.length, chocolates.length);
		System.arraycopy(cookies, 0, tmpCandy, candies.length+filledCandies.length+chocolates.length, cookies.length);
		
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
