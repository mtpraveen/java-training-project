/**
 * 
 */
package brsu.Java.Less2;

import java.util.ArrayList;

/**
 * @author epam0003
 *
 */
public interface IStack<E> {
	void push(E element);
	E pop();
	E top();
	int getSize();
	ArrayList<E> AsErrey();

}
