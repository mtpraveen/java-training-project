package brsu.Java.Less2;

//import bstu.java.pair.Pair;

public class Pair<E> {

	/**
	 * @param args
	 */
	private E first;
	private E second;
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
		return "Pair [first=" + first + ", second=" + second + "]";
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pair<Integer> pair=new Pair<Integer>();
		pair.setFirst(1);
		pair.setSecond(150);
		pair.toString();
		System.out.println((int)pair.getFirst());
		
	}

}
