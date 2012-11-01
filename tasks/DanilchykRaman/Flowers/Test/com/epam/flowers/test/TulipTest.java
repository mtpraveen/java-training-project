package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Rose;
import com.epam.flowers.Tulip;
import com.epam.flowers.typeOfRose;
import com.epam.flowers.typeOfTulip;

public class TulipTest {

	@Test
	public void testTulip() {
		Tulip t = new Tulip("tulip", typeOfTulip.ALTAICA, 100);
		assertEquals("tulip", t.getName());
		assertEquals(100, t.getPrice());
		assertEquals(typeOfTulip.ALTAICA, t.getType());
	}

	@Test
	public void testGetType() {
		Tulip ta = new Tulip("altaica", typeOfTulip.ALTAICA, 100);
		Tulip tc = new Tulip("clusiana", typeOfTulip.CLUSIANA, 200);
		Tulip tm = new Tulip("montana", typeOfTulip.MONTANA, 300);
		assertEquals(typeOfTulip.ALTAICA, ta.getType());
		assertEquals(typeOfTulip.CLUSIANA, tc.getType());
		assertEquals(typeOfTulip.MONTANA, tm.getType());
	}

	@Test
	public void testSetType() {
		Tulip t = new Tulip("Tulip", typeOfTulip.ALTAICA, 100);
		t.setType(typeOfTulip.MONTANA);
		assertEquals(typeOfTulip.MONTANA, t.getType());
	}

	@Test
	public void testGetPriceFromList() {
		Tulip t = new Tulip("Tulip", typeOfTulip.ALTAICA, 100);
		assertEquals(30000, t.getPriceFromList(typeOfTulip.ALTAICA));
	}

}
