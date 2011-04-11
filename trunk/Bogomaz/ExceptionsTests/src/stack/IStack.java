/**
 * 
 */
package stack;

/**
 * @author Евгений
 *
 */
public interface IStack<E> {
	void push(E element);
	E pop() throws Exception;
	E peek() throws Exception;
	int size();
	boolean isEmpty();
	boolean isMember(E element);
	E[] toArray(E[] toArray) throws NullPointerException;
}
