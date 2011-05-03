/**
 * 
 */
package org.training.samples.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	public static class NullPointer extends RuntimeException
	{
		public NullPointer(String message)
		{
			super(message);
		}
	}

	@Override
	public synchronized void push(E element) {
		Element<E> newElement = new Element<E>(element, top);
		size++;
		top = newElement;
	}

	
	
	@Override
	public synchronized  E pop() throws Stack.NullPointer {
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
	
	public static void main(String[] args) {		
		final Stack<Integer> s1 = new Stack<Integer>();
		ExecutorService threads = Executors.newFixedThreadPool(10);
		for (int i=0;i<5;i++){
			final String executorName="Executor#"+i;
			threads.execute(new Runnable(){

				@Override
				public void run() {
					System.out.println(executorName+" is running...");
					try {
						s1.push(10);
						s1.push(20);
						s1.pop();
						s1.pop();
						System.out.println(executorName+" finished...");
					} catch (NullPointer e) {
						System.out.println(e);
					}
					
				}});
		}
		threads.shutdown();
	}


}
