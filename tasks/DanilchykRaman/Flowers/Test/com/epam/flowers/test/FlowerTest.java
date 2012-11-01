package com.epam.flowers.test;

import static org.junit.Assert.*;

import org.junit.*;

import com.epam.flowers.Flower;

import static org.junit.Test.*;

public class FlowerTest {

	@org.junit.Test
	public void testFlower() {
		Flower f = new Flower("flower", 1000);
		assertEquals("flower", f.getName());
		assertEquals(1000, f.getPrice());
	}

	@org.junit.Test
	public void testGetPrice() {
		Flower f = new Flower("", 100);
		assertEquals(100, f.getPrice());
	}

	@org.junit.Test
	public void testSetPrice() {
		Flower f = new Flower("", 100);
		f.setPrice(200);
		assertEquals(200, f.getPrice());
	}

	@org.junit.Test
	public void testGetName() {
		Flower f = new Flower("Flower", 1000);
		assertEquals("Flower", f.getName());
	}

	@org.junit.Test
	public void testSetName() {
		Flower f = new Flower("name", 100);
		f.setName("anotherName");
		String s = f.getName();
		assertEquals("anotherName", s);
	}

}
