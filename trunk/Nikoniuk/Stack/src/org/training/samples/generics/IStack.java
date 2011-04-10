/**
 * 
 */
package org.training.samples.generics;

import org.training.samples.generics.Stack.NullPointer;

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
