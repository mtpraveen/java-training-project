/**
 * 
 */

/**
 * @author Мара
 *
 */
public interface IStack <E> {
	void push(E element);
	E pop();
	E top();
	int getSize(Integer[] a);
	E[] toArray(E[] a);
	boolean isEmpty(Integer[] a);
	boolean isMember(E element, Integer[] a);
}
