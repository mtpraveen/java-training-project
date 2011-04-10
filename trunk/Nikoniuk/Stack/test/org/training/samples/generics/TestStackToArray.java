package org.training.samples.generics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestStackToArray {
	private Stack<Integer> s1 = new Stack<Integer>();

	@Before
	public void createStack() {
		s1.push(10);
		s1.push(20);
		s1.push(10);
		s1.push(30);
	}
	
	@Test
	public void toArrayTest() {
		int []checkList = new int[]{30, 10, 20, 10};
		Integer []data = s1.asArray(new Integer[4]);

		for (int i = 0; i < checkList.length; ++i)
			assertTrue(checkList[i] == data[i]);
	}

}
