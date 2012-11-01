package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Rose;
import com.epam.flowers.typeOfRose;

public class RoseTest {

	@Test
	public void testRose() {
		Rose r = new Rose("rose", typeOfRose.BLACK, 100);
		assertEquals("rose", r.getName());
		assertEquals(100, r.getPrice());
		assertEquals(typeOfRose.BLACK, r.getType());
	}

	@Test
	public void testGetType() {
		Rose rb = new Rose("black", typeOfRose.BLACK, 100);
		Rose rw = new Rose("white", typeOfRose.WHITE, 200);
		Rose rr = new Rose("red", typeOfRose.RED, 300);
		assertEquals(typeOfRose.BLACK, rb.getType());
		assertEquals(typeOfRose.WHITE, rw.getType());
		assertEquals(typeOfRose.RED, rr.getType());
	}

	@Test
	public void testSetType() {
		Rose r = new Rose("rose", typeOfRose.RED, 100);
		r.setType(typeOfRose.WHITE);
		assertEquals(typeOfRose.WHITE, r.getType());
	}

	@Test
	public void testGetPriceFromList() {
		Rose r = new Rose("rose", typeOfRose.WHITE, 100);
		assertEquals(25000, r.getPriceFromList(typeOfRose.BLACK));
	}

}
