package com.epam.flowers;

import java.util.Scanner;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Enter number of flowers: ");
		int num;
		num = s.nextInt();
		
		Bouquet bouquet = new Bouquet(num);
		bouquet.gatherComposition();
		bouquet.printComposition();
		
		System.out.print("Price of the composition is " + bouquet.getPrice());
		s.close();
	}

}
