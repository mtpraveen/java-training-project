/**
 * 
 */
package trainings.pair;

/**
 * @author epam0006
 *
 */
public class Pair<T> {

	private T first;
	private T second;
	/**
	 * @param first
	 * @param second
	 */
	public Pair(T first, T second) {
		super();
		this.first = first;
		this.second = second;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pair<" + first + ", " + second + ">";
	}
	/**
	 * @return the first
	 */
	public T getFirst() {
		return first;
	}
	/**
	 * @param first the first to set
	 */
	public void setFirst(T first) {
		this.first = first;
	}
	/**
	 * @return the second
	 */
	public T getSecond() {
		return second;
	}
	/**
	 * @param second the second to set
	 */
	public void setSecond(T second) {
		this.second = second;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pair<Integer> pair = new Pair<Integer>(0, 0);
		pair.setFirst(100);
		pair.setSecond(200);
		System.out.println(pair);
		
		Pair<String> pair1 = new Pair<String>("100", "200");
		System.out.println(pair1);
		
		Pair pair2 = new Pair<String>("100", "555");
		pair2.setSecond(666);
		System.out.println(pair2);
	}

}
