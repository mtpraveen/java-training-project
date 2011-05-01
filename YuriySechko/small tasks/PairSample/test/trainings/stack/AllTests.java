/**
 * 
 */
package trainings.stack;

import java.util.Collection;
import java.util.LinkedList;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized;

/**
 * @author epam0006
 * 
 */
@RunWith(value = Parameterized.class)
public class AllTests {

	private Integer[] data;
	private Integer[] expected;

	@Parameters
	public static Collection<Integer[][]> data() {
		Collection<Integer[][]> result = new LinkedList<Integer[][]>();
		result.add(new Integer[][] { { 3, 2, 1 }, { 1 } });
		result.add(new Integer[][] { { 3, 1, 3 }, { 3 } });
		return result;
	}

	/**
	 * @param data
	 * @param expected
	 */
	public AllTests(Integer[] data, Integer[] expected) {
		super();
		this.data = data;
		this.expected = expected;
	}

	@Test
	public void pushAndPop() {
		Stack<Integer> stack = new Stack<Integer>();
		for (int element : data) {
			stack.push(element);
		}

		Assert.assertEquals((int) expected[0], (int) stack.top());
	}

	@Test(expected = NullPointerException.class)
	public void testException() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.pop();
	}
}
