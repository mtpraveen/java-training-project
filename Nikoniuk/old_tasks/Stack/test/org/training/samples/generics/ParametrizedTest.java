/**
 * 
 */
package org.training.samples.generics;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * @author epam0001
 *
 */
@RunWith(value=Parameterized.class)
public class ParametrizedTest {
	private Stack<Integer> s1 = new Stack<Integer>();
	
	private int  expected;
	private int value;

	@Parameters
	public static Collection data() {
		return Arrays.asList( new Object[][] {{ 1, 1 }, { 2, 2 }});
	}


	/**
	 * @param expected
	 * @param value
	 */
	public ParametrizedTest(int expected, int value) {
		super();
		this.expected = expected;
		this.value = value;
	}


	@Test
	public void pushAndPop() {
		s1.push(value);
		assertTrue(expected == s1.pop());

	}

}

