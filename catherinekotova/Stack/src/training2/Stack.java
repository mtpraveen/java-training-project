package training2;


public class Stack<E> implements IStack<E> {
	private Element<E> top;
	private int size;
	
	public void setSize(int size) {
		this.size = size;
	}

	class Element<E> {
		private E  value;
		public E getValue() {
			return value;
		}
		public void setValue(E value) {
			this.value = value;
		}
		private Element<E> previous;
		public Element(E value, Element<E> previous) {
			super();
			this.value = value;
			this.previous = previous;
		}
		public Element<E> getPrevious() {
			return previous;
		}
		public void setPrevious(Element<E> previous) {
			this.previous = previous;
		}
	}
	/**
	 *  insert stack element 
	 */
	public void push(E element){
	Element<E> newElement = new Element<E>(element,top);
	size++;
	top=newElement;
	}
	/**
	 *  delete stack element
	 */

	public E pop() {
	E m =top.getValue();
		top=top.getPrevious();
		return m;
	}
		/**
		 *  getting stack size
		 */
	public int getSize() {
		
		return size;
	}
	/**
	 *  getting top element
	 */
	public E top() {
		return top.getValue();
		
	}
	/**
	 *  creating stack, getting size, top element, delete some elements, getting new top elenent 
	 * @param args
	 */
public static void main(String[] args) {
	Stack<Integer> s1= new Stack<Integer>();
	s1.push(10);
	s1.push(20);
	s1.push(15);
	s1.push(5);
	
	System.out.println("size"+s1.getSize());
	System.out.println("top="+s1.top());
	s1.pop();
	s1.pop();
	s1.pop();
	System.out.println("top="+s1.top());
	

}

@Override
public void top(E element) {
	// TODO Auto-generated method stub
	
}
}
