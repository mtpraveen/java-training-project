package com.epam.training.logic;

import java.util.ArrayList;

/**
 * Interface determine processing with the stack.
 * @author EXUMLOKE
 * @param <E> type of elements that can be in the stack.
 */
public interface IStack<E> extends Iterable<E> {
	public void push(E element); // add element to the top of the stack
	public E pop(); // remove the element at the top of the stack
	public E peek(); // get the element at the top of he stack
	public int size(); // number of elements in the stack
	public boolean isEmpty(); // determine if the stack is empty
	public boolean isMember(E element); // determine if the element in the stack
	public E[] toArray(ArrayList<E> toArray); // copy elements from the stack to the array 
}