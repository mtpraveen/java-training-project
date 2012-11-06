package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Lily;
import com.epam.flowers.LilyTypes;

public class LilyTest {

	@Test
	public void testLily() {
		Lily l = new Lily("Lily", LilyTypes.CASSANDRA, 100);
		assertEquals("Lily", l.getName());
		assertEquals(100, l.getPrice());
		assertEquals(LilyTypes.CASSANDRA, l.getType());
	}

	@Test
	public void testGetType() {
		Lily lc = new Lily("cassandra", LilyTypes.CASSANDRA, 100);
		Lily lg = new Lily("golden", LilyTypes.GOLDEN, 200);
		Lily lp = new Lily("pink", LilyTypes.PINK, 300);
		assertEquals(LilyTypes.CASSANDRA, lc.getType());
		assertEquals(LilyTypes.GOLDEN, lg.getType());
		assertEquals(LilyTypes.PINK, lp.getType());
	}

	@Test
	public void testSetType() {
		Lily l = new Lily("Lily", LilyTypes.CASSANDRA, 100);
		l.setType(LilyTypes.GOLDEN);
		assertEquals(LilyTypes.GOLDEN, l.getType());
	}

	@Test
	public void testGetPriceFromList() {
		Lily l = new Lily("Lily", LilyTypes.GOLDEN, 100);
		assertEquals(33000, LilyTypes.GOLDEN.getPrice());
	}

}
