/**
 * 
 */
package org.training.samples.generics;

import java.util.Arrays;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.training.samples.generics.Stack.NullPointer;

/**
 * @author epam0001
 *
 */
public class StackTest {
	
	private Stack<Integer> s1 = new Stack<Integer>();

	@Before
	public void createStack() {
		s1.push(10);
		s1.push(20);
	}
	
	@Test
	public void checkStackOperations() {

		s1.push(10);
		s1.push(30);
		
		assertTrue(s1.top() == 30);
		
		assertEquals("Stack size is bad", s1.getSize(), 4);
		
		try {
			s1.pop();
			assertTrue(s1.pop() == 10);
		}
		catch (Stack.NullPointer e) {
			fail("NullPointer mustn't occur");
		}
		
		
		assertEquals("Stack size is bad", s1.getSize(), 2);

	}
	
	@Test
	public void checkNullPointerException() {
		try
		{
			System.out.println("popped value " + s1.pop());
			System.out.println("popped value " + s1.pop());
			System.out.println("popped value " + s1.pop());
			fail("Stack.NullPointer exception mush occur");
		}
		catch (Stack.NullPointer e)
		{
		}
	}
}
