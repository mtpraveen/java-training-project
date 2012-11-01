package com.epam.flowers;

public class Application {

	/**
	 * Task:
	 * Define a hierarchy of flowers.
	 * Create some objects of flowers.
	 * Gather bouquet with determination of it price.
	 */
	public static void main(String[] args) {
			
		Rose rose = new Rose("White rose", typeOfRose.WHITE, Rose.getPriceFromList(typeOfRose.WHITE));
		Tulip tulip = new Tulip("Tulip altaica", typeOfTulip.ALTAICA, Tulip.getPriceFromList(typeOfTulip.ALTAICA));
		Lily lily = new Lily("Lily cassandra", typeOfLily.CASSANDRA, Lily.getPriceFromList(typeOfLily.CASSANDRA));
			
		Bouquet bouquet = new Bouquet();
		bouquet.addFlower(rose);
		bouquet.addFlower(tulip);
		bouquet.addFlower(lily);
		
		bouquet.print();
		
		System.out.print("Price of the composition is " + bouquet.getPrice());
	}

}
