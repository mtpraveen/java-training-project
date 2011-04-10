/**
 * 
 */
package org.training.samples.generics;

/**
 * @author epam0001
 *
 */
public class Pair<E> {
	private E first;
	private E second;
	
	public Pair() {
		first = null;
		second = null;
	}

	/**
	 * @param first
	 * @param second
	 */
	public Pair(E first, E second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first
	 */
	public E getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(E first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public E getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(E second) {
		this.second = second;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pair<" + first + "," + second + ">";
	}
	
	public static void main(String[] args)
	{
		Pair<Integer> pair = new Pair<Integer>();
		pair.setFirst(100);
		pair.setSecond(200);
		System.out.println(pair);
		
		Pair<String> pair1 = new Pair<String>();
		pair1.setFirst("100!");
		pair1.setSecond("200!");
		System.out.println(pair1);
		
		Pair pair2 = new Pair<String>();
		pair2.setFirst(100);
		pair2.setSecond("200!");
		System.out.println(pair2);
		
		System.out.println(((Integer)pair2.getFirst()) * 100);
		System.out.println(((Integer)pair2.getSecond()) * 100);
	}

}
