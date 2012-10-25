package com.epam.flowers;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Flower.typeOfFlower;

public class FlowerTest {

	/** Tests for class Flower*/
	@Test
	public void testGetPrice() {
		Flower flower = new Flower(typeOfFlower.ROSE);
		assertEquals(20000, flower.getPrice());
	}

	@Test
	public void testGetType() {
		Flower flower = new Flower(typeOfFlower.TULIP);
		assertEquals(typeOfFlower.TULIP, flower.getType());
	}
}
