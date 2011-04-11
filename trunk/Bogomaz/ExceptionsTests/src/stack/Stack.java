/**
 * 
 */
package stack;

import java.util.*;

/**
 * @author Евгений
 *
 */
public class Stack<T> implements IStack<T>{
	
	private Stack<T> previous;
	private T value;
	private int size;
	
	public Stack(){
		this.previous = null;
		this.value = null;
		size = 0;
	}
	private Stack(Stack<T> previous, T value){
		this.previous = previous;
		this.value = value;
		size = 0;
	}
	
	/* (non-Javadoc)
	 * @see atd.IStack#push(java.lang.Object)
	 */
	@Override
	public void push(T element) {
		// TODO Auto-generated method stub
		Stack<T> stack = new Stack<T>(this.previous, this.value);
		this.previous = stack;
		this.value = element; 
		size++;
	}

	/* (non-Javadoc)
	 * @see atd.IStack#pop()
	 */
	@Override
	public T pop() throws Exception{
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new Exception("Impossible extract top of stack because stack is empty!");
		T tempV = null;
		if (this.previous != null){
			tempV = this.value;
			this.value = this.previous.value;
			this.previous = this.previous.previous;
			size--;
		}
		return tempV;
	}

	/* (non-Javadoc)
	 * @see atd.IStack#peek()
	 */
	@Override
	public T peek() throws Exception{
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new Exception("Impossible view top of stack because stack is empty!");
		return this.value;
	}

	/* (non-Javadoc)
	 * @see atd.IStack#size()
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/* (non-Javadoc)
	 * @see atd.IStack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return previous == null;
	}

	/* (non-Javadoc)
	 * @see atd.IStack#isMember(java.lang.Object)
	 */
	@Override
	public boolean isMember(Object element) {
		// TODO Auto-generated method stub
		Stack<T> pointer = this;
		while (pointer.previous != null){
			if (pointer.value == element)
				return true;
			pointer = pointer.previous;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see atd.IStack#toArray(E[])
	 */
	@Override
	public T[] toArray(T[] toArray) throws NullPointerException{
		// TODO Auto-generated method stub
		if (toArray == null)
			throw new NullPointerException("Impossible convert stack to array becouse undefined type T!");
		List<T> tempL = new ArrayList<T>(this.size);
		Stack<T> pointer = this;
		while (pointer.previous != null){
			tempL.add(pointer.value);
			pointer = pointer.previous;
		}
		T[] tempA = tempL.toArray(toArray);
		T tempT; 
		for (int i = 0; i < tempA.length / 2; i++){
			tempT = tempA[i];
			tempA[i] = tempA[tempA.length - i - 1];
			tempA[tempA.length - i - 1] = tempT;
		}
		return tempA; 
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		// TODO Auto-generated method stub 
		Stack<T> tempStack = new Stack<T>();
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		Stack<T> pointer = this;
		while (pointer.previous != null){
			tempStack.push(pointer.value);
			pointer = pointer.previous;
		}
		try{
		if (!tempStack.isEmpty())
			sb.append(tempStack.pop());
		while (!tempStack.isEmpty())
			sb.append(", " + tempStack.pop());
		}
		catch(Exception exc){System.out.println(exc.getMessage());}
		finally{
			sb.append(")");
		}
		return sb.toString();
	}

	

}
