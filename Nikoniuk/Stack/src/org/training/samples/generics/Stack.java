/**
 * 
 */
package org.training.samples.generics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author epam0001
 *
 */
public class Stack<E> implements IStack<E> {
	private Element<E> top;
	private int size;
	class Element<E> {
		private E value;
		private Element<E> previous;
		
		
		/**
		 * @return the value
		 */
		public E getValue() {
			return value;
		}
		
		
		/**
		 * @param value the value to set
		 */
		public void setValue(E value) {
			this.value = value;
		}
		
		/**
		 * @param value
		 * @param previous
		 */
		public Element(E value, Element<E> previous) {
			this.value = value;
			this.previous = previous;
		}


		/**
		 * @param previous the previous to set
		 */
		public void setPrevious(Element<E> previous) {
			this.previous = previous;
		}


		/**
		 * @return the previous
		 */
		public Element<E> getPrevious() {
			return previous;
		}
		
		
		
	}
	
	public static class NullPointer extends Exception
	{
		public NullPointer(String message)
		{
			super(message);
		}
	}

	@Override
	public void push(E element) {
		Element<E> newElement = new Element<E>(element, top);
		size++;
		top = newElement;
	}

	
	
	@Override
	public E pop() throws Stack.NullPointer {
		if (top == null)
			throw new Stack.NullPointer("Empty stack: exception in pop() method");
		E result =  top.getValue();
		top = top.getPrevious();
		--size;
		return result;
	}

	@Override
	public E top() {
		return top.getValue();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public E[] asArray(E[] input) {
		ArrayList<E> result = new ArrayList<E>();
		Element<E> current = top;
		
		while (current != null)
		{
			result.add(current.value);
			current = current.getPrevious();
		}
		return result.toArray(input);
	}
	
	public static void main(String [] args) {
		Stack<Integer> s1 = new Stack<Integer>();
		s1.push(10);
		s1.push(20);
		try
		{
			System.out.println("popped value" + s1.pop());
			System.out.println("popped value" + s1.pop());
			System.out.println("popped value" + s1.pop());
		}
		catch (Stack.NullPointer e)
		{
			System.err.println(e.getMessage());
		}
		s1.push(10);
		s1.push(30);
		
		System.out.println(Arrays.toString(s1.asArray(new Integer[]{})));
		System.out.println("size =" + s1.getSize());
		System.out.println(s1.top());
		Integer el1 = new Integer(1);
		System.out.println("size =" + s1.getSize());
	}

}
