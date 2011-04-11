package test;

import org.junit.Test;
import stack.Stack;

public class TestStackException {
	
	@Test(expected=Exception.class)
	public void popException() throws Exception{
		Stack<Integer> st = new Stack<Integer>();
		st.pop();
	}
	
	@Test(expected=Exception.class)
	public void peekException() throws Exception{
		Stack<Integer> st = new Stack<Integer>();
		st.peek();
	} 
	
	@Test(expected=NullPointerException.class)
	public void toArrayException(){
		Stack<Integer> st = new Stack<Integer>();
		st.toArray(null);
	}
}
