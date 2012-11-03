package com.epam.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.entity.Necklace;
import com.epam.entity.PreciousStone;
import com.epam.entity.SemiPreciousStone;
import com.epam.entity.Stone;

public class TestStone {

	private static Stone s1;
	private static Stone s2;
	private static Stone s3;
	private static Stone s4;
	private static Stone s5;
	private static Necklace necklace;
	
	@BeforeClass
	public static void createNecklace() {
		s1 = new PreciousStone("preciousStone1", 11, 120);
		s2 = new PreciousStone("preciousStone2", 15, 300);
		s3 = new SemiPreciousStone("semiPreciousStone1", 10, 70);
		s4 = new SemiPreciousStone("semiPreciousStone2", 12, 80);
		s5 = new SemiPreciousStone("semiPreciousStone3", 14, 90);
		necklace = new Necklace();
		necklace.addStone(s3);
		necklace.addStone(s1);
		necklace.addStone(s2);
		necklace.addStone(s4);
		necklace.addStone(s5);
	}
	
	@Test
	public void testTotalWeight() {
		assertEquals(necklace.totalWeight(), 62.0, 0.0001);
	}
	
	@Test
	public void testTotalPrice() {
		assertEquals(necklace.totalPrice(), 660.0, 0.0001);
	}
	
	@Test
	public void testSortByWeight() {
		List<Stone> list = new ArrayList<Stone>();
		list.add(s3);
		list.add(s1);
		list.add(s4);
		list.add(s5);
		list.add(s2);
		necklace.sortByWeight();
		assertEquals(list, necklace.getStones());
	}
	
	@Test
	public void testSortByPrice() {
		List<Stone> list = new ArrayList<Stone>();
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s1);
		list.add(s2);
		necklace.sortByPrice();
		assertEquals(list, necklace.getStones());
	}
	
	@Test
	public void testSearchByPrice(){
		double min = 70;
		double max = 90;
		List<Stone> list = new ArrayList<Stone>();
		list.add(s3);
		list.add(s4);
		list.add(s5);
		assertEquals(list, necklace.searchByPrice(min, max));
	}
	
	@Test
	public void testSearchByWeight(){
		double min = 10;
		double max = 12;
		List<Stone> list = new ArrayList<Stone>();
		list.add(s3);
		list.add(s1);
		list.add(s4);
		necklace.sortByWeight();
		assertEquals(list, necklace.searcByWeight(min, max));
	}
}
