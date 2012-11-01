package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Bouquet;
import com.epam.flowers.Flower;

public class BouquetTest {

	@Test
	public void testBouquet() {
		Bouquet b = new Bouquet();
		assertEquals(0, b.getNumberOfFlowers());
	}

	@Test
	public void testGetPrice() {
		Bouquet b = new Bouquet();
		b.addFlower(new Flower("rose", 20000));
		b.addFlower(new Flower("tulip", 15000));
		assertEquals(35000, b.getPrice());
	}

	@Test
	public void testAddFlower() {
		Bouquet b = new Bouquet();
		b.addFlower(new Flower("flower", 20000));
		assertEquals(1, b.getNumberOfFlowers());
	}

	@Test
	public void testGetNumberOfFlowers() {
		Bouquet b = new Bouquet();
		b.addFlower(new Flower("flower1", 20000));
		b.addFlower(new Flower("flower2", 20000));
		b.addFlower(new Flower("flower3", 20000));
		assertEquals(3, b.getNumberOfFlowers());
	}

}
