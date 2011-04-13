//import java.util.ArrayList;

import java.util.List;

public interface IStack<E> {
	void push(E element);
	E pop();
	int Size();
	List <E> toArray();
	E peek();
	
	boolean isEmpty();
	boolean isMember(E element);
	
	String toString();
}