package test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import stack.*;

@RunWith(value= Parameterized.class)
public class TestStack {
	
	private Integer value;
	private Integer expected;
	
	public TestStack(Integer expected, Integer value){
		this.expected = expected;
		this.value = value;
	}
	
	@Parameters
	public static Collection<Integer[]> getTestParameters(){
		return Arrays.asList(new Integer[][]{{1, 1}, {2, 2}, {3, 3}});
	}
	
	@Test
	public void push(){
		Stack<Integer> st = new Stack<Integer>();
		st.push(value);
		assertEquals(1, st.size());
	}
	
	@Test
	public void pop() throws Exception{
		Stack<Integer> st = new Stack<Integer>();
		st.push(value);
		assertEquals(expected, st.pop());
	}
	
	@Test
	public void peek() throws Exception{
		Stack<Integer> st = new Stack<Integer>();
		st.push(value);
		assertEquals(expected, st.peek());
	} 
	
	@Test
	public void isMember(){
		Stack<Integer> st = new Stack<Integer>();
		st.push(value);
		assertTrue(st.isMember(expected));
	}
	
	@Test
	public void toArray(){
		Stack<Integer> st = new Stack<Integer>();
		st.push(value);
		st.push(value);
		assertArrayEquals(st.toArray(new Integer[]{}), new Integer[]{expected, expected});
	}
	
	@Test
	public void testToString(){
		Stack<Integer> st = new Stack<Integer>();
		st.push(value);
		st.push(value);
		assertEquals("(" + expected + ", " + expected + ")", st.toString());
	}
}

