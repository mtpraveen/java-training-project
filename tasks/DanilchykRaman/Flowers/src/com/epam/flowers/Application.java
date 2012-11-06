package com.epam.flowers;

public class Application {

	/**
	 * Task:
	 * Define a hierarchy of flowers.
	 * Create some objects of flowers.
	 * Gather bouquet with determination of it price.
	 */
	public static void main(String[] args) {
			
		Rose rose = new Rose("White rose", RoseTypes.WHITE, RoseTypes.WHITE.getPrice());
		Tulip tulip = new Tulip("Tulip altaica", TulipTypes.ALTAICA, TulipTypes.ALTAICA.getPrice());
		Lily lily = new Lily("Lily cassandra", LilyTypes.CASSANDRA, LilyTypes.CASSANDRA.getPrice());
			
		Bouquet bouquet = new Bouquet();
		bouquet.addFlower(rose);
		bouquet.addFlower(tulip);
		bouquet.addFlower(lily);
		
		System.out.println(bouquet);
		
		System.out.print("Price of the composition is " + bouquet.getPrice());
	}

}
