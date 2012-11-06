package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.epam.flowers.Rose;
import com.epam.flowers.Tulip;
import com.epam.flowers.RoseTypes;
import com.epam.flowers.TulipTypes;

public class TulipTest {

	@Test
	public void testTulip() {
		Tulip t = new Tulip("tulip", TulipTypes.ALTAICA, 100);
		assertEquals("tulip", t.getName());
		assertEquals(100, t.getPrice());
		assertEquals(TulipTypes.ALTAICA, t.getType());
	}

	@Test
	public void testGetType() {
		Tulip ta = new Tulip("altaica", TulipTypes.ALTAICA, 100);
		Tulip tc = new Tulip("clusiana", TulipTypes.CLUSIANA, 200);
		Tulip tm = new Tulip("montana", TulipTypes.MONTANA, 300);
		assertEquals(TulipTypes.ALTAICA, ta.getType());
		assertEquals(TulipTypes.CLUSIANA, tc.getType());
		assertEquals(TulipTypes.MONTANA, tm.getType());
	}

	@Test
	public void testSetType() {
		Tulip t = new Tulip("Tulip", TulipTypes.ALTAICA, 100);
		t.setType(TulipTypes.MONTANA);
		assertEquals(TulipTypes.MONTANA, t.getType());
	}

	@Test
	public void testGetPriceFromList() {
		Tulip t = new Tulip("Tulip", TulipTypes.ALTAICA, 100);
		assertEquals(30000, TulipTypes.ALTAICA.getPrice());
	}

}
