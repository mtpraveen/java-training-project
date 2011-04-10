/**
 * 
 */
package trainings.pair;

/**
 * @author epam0006
 *
 */
public interface IStack<E> {
	/**
	 * add new element
	 * @param element element to add
	 */
	void push(E element);
	
	/**
	 * delete top element
	 */
	void pop();
	
	/**
	 * returns top element without deleting
	 * @return
	 */
	E top();
	
	/**
	 * returns actual size of stack
	 * @return
	 */
	int getSize();
	
	/**
	 * returns stack as array, top element will be last in array
	 * @return
	 */
	E[] asArray();
	
	/**
	 * checks stack size
	 * @return true if stack is empty
	 */
	boolean isEmpty();
	
	/**
	 * sorts stack in direct order
	 */
	void sort();
	
	/**
	 * sorts stack in reverse order
	 */
	void rsort();
}
