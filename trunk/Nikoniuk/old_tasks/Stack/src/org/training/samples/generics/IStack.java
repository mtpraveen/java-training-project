/**
 * 
 */
package org.training.samples.generics;


/**
 * @author epam0001
 *
 */
public interface IStack<E> {
	void push(E element);
	E pop() throws Stack.NullPointer;
	E top();
	int getSize();
	E[] asArray(E[] input);
}
