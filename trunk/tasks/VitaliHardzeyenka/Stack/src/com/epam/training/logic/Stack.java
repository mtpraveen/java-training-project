package com.epam.training.logic;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

/**
 * 
 * @author EXUMLOKE
 *
 * @param <E>
 */
public class Stack<E> implements IStack<E> {

	ArrayList<E> stack = new ArrayList<>();
	private final Logger logger = Logger.getLogger(Stack.class);
	private final ConsoleAppender consoleAppender = new ConsoleAppender(new SimpleLayout());
	
	/**
	 * Constructor.
	 */
	public Stack() {
		logger.addAppender(consoleAppender);
	}
	
	
	@Override
	/**
	 * Return parameterize iterator for the stack.
	 * @return parameterize iterator. 
	 */
	public Iterator<E> iterator() {
		try {
			Iterator<E> iterator = stack.iterator();
			return iterator;
		} catch(NullPointerException exception) {
			logger.error("Stack is not exist " + exception);
			return null;
		}
	}

	@Override
	/**
	 * Add element to the top of the stack.
	 * @param element with type E that will be added in the end of the stack.
	 */
	public void push(E element) {
		try {
			stack.add(element);
		} catch (Exception exception) {
			logger.error("Stack is not exist " + exception);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Remove the element at the top of the stack.
	 * @return parameter of deleting element.
	 */
	public E pop() {
		try {
			int lastElementNumer = stack.size() - 1;
			Object type = stack.get(lastElementNumer).getClass();
			stack.remove(lastElementNumer);
			return (E) type;
		} catch (NullPointerException exception) {
			logger.error("Stack is not exist " + exception);
			return null;
		}
	}

	@Override
	/**
	 * Number of elements in the stack.
	 */
	public int size() {
		try {
			return stack.size();
		} catch (NullPointerException exception) {
			logger.error("Stack is not exist " + exception);
			return 0;
		}
	}

	@Override
	/**
	 * Determine if the stack is empty.
	 * @return state of stack: true - if empty, false if not.
	 */
	public boolean isEmpty() {
		return stack.isEmpty() ? true : false;
	}

	@Override
	/**
	 * Determine if the element in the stack
	 * @param <E> type of the element.
	 * @param element its element that will be checked for belonging to the stack.
	 * @return true is belong, false if not. 
	 */
	public boolean isMember(E element) {		
		return stack.contains(element) ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * 
	 */
	public E[] toArray(ArrayList<E> toArray) {
		return (E[]) ArrayUtils.toArray(toArray);
	}

	@Override
	/**
	 * Get element at the top of the stack.
	 * @return first element.
	 */
	public E peek() {
		try {
			return stack.get(0);
		} catch (NullPointerException exception) {
			logger.error("Stack not exist" + exception);
			return null;
		} catch (ArrayIndexOutOfBoundsException exception) {
			logger.error("Index out of bound" + exception);
			return null;
		}
	}
}
