package training2;

public interface IStack<E> {
	void push(E element);
	E pop();
	void top(E element);
	E top();
	int getSize();
	
	

}
