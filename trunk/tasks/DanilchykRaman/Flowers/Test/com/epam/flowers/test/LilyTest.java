package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Lily;
import com.epam.flowers.typeOfLily;

public class LilyTest {

	@Test
	public void testLily() {
		Lily l = new Lily("Lily", typeOfLily.CASSANDRA, 100);
		assertEquals("Lily", l.getName());
		assertEquals(100, l.getPrice());
		assertEquals(typeOfLily.CASSANDRA, l.getType());
	}

	@Test
	public void testGetType() {
		Lily lc = new Lily("cassandra", typeOfLily.CASSANDRA, 100);
		Lily lg = new Lily("golden", typeOfLily.GOLDEN, 200);
		Lily lp = new Lily("pink", typeOfLily.PINK, 300);
		assertEquals(typeOfLily.CASSANDRA, lc.getType());
		assertEquals(typeOfLily.GOLDEN, lg.getType());
		assertEquals(typeOfLily.PINK, lp.getType());
	}

	@Test
	public void testSetType() {
		Lily l = new Lily("Lily", typeOfLily.CASSANDRA, 100);
		l.setType(typeOfLily.GOLDEN);
		assertEquals(typeOfLily.GOLDEN, l.getType());
	}

	@Test
	public void testGetPriceFromList() {
		Lily l = new Lily("Lily", typeOfLily.GOLDEN, 100);
		assertEquals(33000, l.getPriceFromList(typeOfLily.GOLDEN));
	}

}
