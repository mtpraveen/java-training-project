/**
 * 
 */
package trainings.stack;

import java.util.Arrays;

import trainings.pair.IStack;
/**
 * @author epam0006
 *
 */
public class Stack<E> implements IStack<E> {
	/**
	 * class for internal representation of element
	 * @author yura
	 *
	 * @param <E>
	 */
	class Element<E> {
		/**
		 * @return the previous
		 */
		public Element<E> getPrevious() {
			return previous;
		}
		/**
		 * @param previous the previous to set
		 */
		public void setPrevious(Element<E> previous) {
			this.previous = previous;
		}
		private E value;
		private Element<E> previous;
		/**
		 * @return the value
		 */
		public E getValue() {
			return value;
		}
		/**
		 * @param value the value to set
		 */
		public void setValue(E value) {
			this.value = value;
		}
		/**
		 * @param value
		 * @param previous
		 */
		public Element(E value, Element<E> previous) {
			this.value = value;
			this.previous = previous;
		}
		
		
	}
	private Element<E> top;
	int size;
	
	@Override
	public void push(E element) {
		Element<E> newElement = new Element<E>(element, top);
		top = newElement;
		size++;
	}

	@Override
	public void pop() throws NullPointerException {
		if ( top == null ) {
			throw new NullPointerException();
		}
		top = top.getPrevious();
		size--;
	}

	@Override
	public E top() {
		if ( top == null ) {
			throw new NullPointerException();
		}
		return top.getValue();
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public E[] asArray() {
		Object[] resultArray = new Object[size];
		
		int i = resultArray.length - 1; 
		for(Element<E> curElement = top; curElement != null; ) {
			Object newValue = new Object();
			newValue = (E)curElement.getValue();
			resultArray[i] =  newValue;
			curElement = curElement.getPrevious();
			i--;
		}
		return (E[])resultArray;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String resultStr =  "Stack [size = " + size + "]: ";
		Object[] array = this.asArray();
		StringBuilder sb = new StringBuilder("Stack [size=" + size + "]: ");
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i]).append("  ");
		}
		return sb.toString();
	}



	/* (non-Javadoc)
	 * @see trainings.pair.IStack#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return top == null;
	}


	/* (non-Javadoc)
	 * @see trainings.pair.IStack#sort()
	 */
	@Override
	public void sort() {
		Object[] tempArray = this.asArray();
		Arrays.sort(tempArray);
		Stack<E> sortedStack= new Stack<E>(); // new sorted stack
		for( Object curValue : tempArray) {
			Object newValue = new Object();
			newValue = curValue;
			sortedStack.push((E)newValue);
		}	
		
		top = sortedStack.top;
	}


	/* (non-Javadoc)
	 * @see trainings.pair.IStack#rsort()
	 */
	@Override
	public void rsort() {
		Object[] tempArray = this.asArray();
		// reverse sort is not working, now using direct sort
		Arrays.sort(tempArray);
		Stack<E> sortedStack= new Stack<E>(); // new sorted stack
		for( Object curValue : tempArray) {
			Object newValue = new Object();
			newValue = curValue;
			sortedStack.push((E)newValue);
		}	
		
		top = sortedStack.top;
	}
	
}
