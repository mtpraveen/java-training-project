import java.util.ArrayList;

import java.util.List;

/**
 * 
 */

/**
 * @author ANN
 * 
 */
public class Stack<E> implements IStack<E> {

	public Element<E> head;
	public int size;

	public class Element<E> {

		private Element<E> previous;
		private E value;

		public Element<E> getPrevious() {
			return previous;
		}

		public void setPrevious(Element<E> previous) {
			this.previous = previous;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

	}

	public void push(E newElement) {
		Element<E> element = new Element<E>();
		element.setValue(newElement);
		element.setPrevious(head);
		head = element;
		size++;

	}

	public E pop() {

		E value = head.getValue();
		head = head.getPrevious();
		size--;

		return value;

	}

	public int Size() {
		return size;
	}

	public List<E> toArray() {

		List<E> mass = new ArrayList<E>();
		mass.add(head.value);
		Element<E> link = head.getPrevious();
		while (link != null) {
			mass.add(link.value);
			link = link.getPrevious();
		}

		return mass;

	}

	public E peek() {
		E value = head.getValue();
		return value;
	}

	public boolean isEmpty() {
		if (head == null)
			return true;
		else
			return false;
	}

	public boolean isMember(E element) {
		boolean member = false;

		Element<E> link = head;
		while (link != null) {
			if (element == link.getValue())
				member = true;
			link = link.getPrevious();

		}
		return member;
	}

	public String toString() {

		return "Stack<" + toArray() + ">";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		int value = 1;
		boolean empty = s.isEmpty();
		if (empty)
			System.out.println("Stack is empty");
		
		System.out.println("Pop element:");
		for (int i = 0; i < 5; i++) {
			s.push(value);
			System.out.println(value);
			value *= 10;
		}
		empty = s.isEmpty();
		if (!empty) {
			int head = s.peek();

			System.out.println("The peek of stack:" + head);

			System.out.println("The size of stack:" + s.Size());
			System.out.println("Stack as array:" + s.toArray());
			value = 100;
			boolean member = s.isMember(value);
			if (member)
				System.out.printf("Value %d is member of stack \n", value);

			String string = s.toString();
			System.out.println("Stack as string:" + string);
			System.out.println("Pop all elements:\n");
			while (s.head != null) {
				int el = s.pop();
				System.out.println(el);
			}

		}
	}

}
