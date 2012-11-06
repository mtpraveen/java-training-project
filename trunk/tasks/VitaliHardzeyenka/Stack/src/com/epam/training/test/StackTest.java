package com.epam.training.test;

import com.epam.training.logic.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class StackTest {

	@Test
	public void testPush() {
		
	}
	
	@Test
	public void testPop() {
		
	}
	
	@Test
	public void testPeek() {
		
	}
	
	@Test
	public void testSize() {
		
	}
	
	@Test
	public void testIsEmpty() {
		
	}
	
	@Test
	public void testIsMember() {
		
	}
	
	@Test
	public void testToArray() {
		ArrayList<Integer> integers = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		
		integers.add(0);
		integers.add(1);
		integers.add(2);
		integers.add(3);
		integers.add(4);
		integers.add(5);
		integers.add(6);
		
		System.out.println(Arrays.toString(stack.toArray(integers)));
	}

}
