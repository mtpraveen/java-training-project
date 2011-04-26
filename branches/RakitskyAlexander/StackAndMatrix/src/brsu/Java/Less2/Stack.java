package brsu.Java.Less2;

import java.util.ArrayList;
/**
 * @author Rakitsy Alexander
 *
 */
public class Stack<E> implements IStack<E> {
	/**
	 * @param top
	 * @param size
	 */
	private Element<E> top;
	private int size;
	/**
	 * This copying Construction, he coping object
	 * @param top
	 * @param size
	 */
	public Stack(Element<E> top, int size) {
		super();
		this.top = top;
		this.size = size;
	}
	/**
	 * This null construction 
	 */
	public Stack() {
		super();
		this.top = null;
		this.size =0;
	}

	/**
	 * @see brsu.Java.Less2.IStack#AsErrey()
	 * @return list - new array or if size=0 then return null 
	 */

	@Override
	public ArrayList<E> AsErrey() {
		if (size!=0)
		{
			ArrayList<E> list=new ArrayList<E>();
			Element<E> addv=this.top;
			for(int i=0;i<size;i++)
			//while (!addv.getPrivios())
			{
				list.add(addv.getValue());
				addv=addv.getPrivios();
			}
			return list;
				
		}
				// TODO Auto-generated method stub
		return null;
	}

	class Element<E>{
		/**
		 * @param value
		 * @param previos
		 */
		private E value;
		private Element<E> privios;
		/**
		 * @return value - count the top element
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
		 * @param privios the privios to set
		 */
		public void setPrivios(Element<E> privios) {
			this.privios = privios;
		}
		/**
		 * This construction, why add element for top of stack
		 * @param element and top1 for top of stack
		 */
		Element(E element,Element top1)
		{
			this.setPrivios(top1);
			this.setValue(element);
		}

		/**
		 * @return the privios
		 */
		public Element<E> getPrivios() {
			return privios;
		}
		
	}
	
	/* 
	 * @see brsu.Java.Less2.IStack#push(java.lang.Object)
	 * @param element,why add of stack for top
	 * 
	 */
	@Override
	/**
	 * this function add element of stack for top
	 * @param element 
	 */
	public void push(E element) {
		Element<E> newElement=new Element<E>(element,top);
			size++;
			top=newElement;
			
	}
		
	
	/** 
	 * @see brsu.Java.Less2.IStack#pop(java.lang.Object)
	 * This function delete first elements of stack and return top of stack
	 * @return top of stack
	 */
	@Override
	public E pop() {
		// TODO Auto-generated method stub
		E temp= top.value;
		if (top!=null)
		{
			size--;
			if (size!=0)
				top=top.privios;
			else
				top.value=null;
		}
		return temp;
	}

	/** 
	 * @see brsu.Java.Less2.IStack#top()
	 * @return top of stack
	 */
	@Override
	public E top() {
		// TODO Auto-generated method stub
		return this.top.getValue();
	}

	/**
	 * @see brsu.Java.Less2.IStack#getSize()
	 * @return get size
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	/** 
	 * this function transforms toStting()
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Stack [top=" + top.value + ", size=" + size + "]";
	}
	public static void main(String[] arg)
	{
		Stack<Integer> myStack=new Stack<Integer>();
		myStack.push(10);
		myStack.push(111);
		myStack.push(111);
		myStack.push(111);
		myStack.push(111);
		System.out.println(myStack.toString());
		System.out.println(myStack.pop());
		System.out.println(myStack.toString());
		System.out.println(myStack.pop());
		System.out.println(myStack.toString());
		ArrayList<Integer> mas=new ArrayList<Integer>();
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		mas=myStack.AsErrey();
		System.out.println(mas.get(0));
		System.out.println(mas.toString());
	}
	

}
