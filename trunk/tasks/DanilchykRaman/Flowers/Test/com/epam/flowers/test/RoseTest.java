package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Rose;
import com.epam.flowers.RoseTypes;

public class RoseTest {

	@Test
	public void testRose() {
		Rose r = new Rose("rose", RoseTypes.BLACK, 100);
		assertEquals("rose", r.getName());
		assertEquals(100, r.getPrice());
		assertEquals(RoseTypes.BLACK, r.getType());
	}

	@Test
	public void testGetType() {
		Rose rb = new Rose("black", RoseTypes.BLACK, 100);
		Rose rw = new Rose("white", RoseTypes.WHITE, 200);
		Rose rr = new Rose("red", RoseTypes.RED, 300);
		assertEquals(RoseTypes.BLACK, rb.getType());
		assertEquals(RoseTypes.WHITE, rw.getType());
		assertEquals(RoseTypes.RED, rr.getType());
	}

	@Test
	public void testSetType() {
		Rose r = new Rose("rose", RoseTypes.RED, 100);
		r.setType(RoseTypes.WHITE);
		assertEquals(RoseTypes.WHITE, r.getType());
	}

	@Test
	public void testGetPriceFromList() {
		Rose r = new Rose("rose", RoseTypes.WHITE, 100);
		assertEquals(23000, RoseTypes.WHITE.getPrice());
	}

}
