/**
 * 
 */
package collections;

import java.util.Iterator;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import collections.Tree.Node;

/**
 * @author Pechko_E
 * 
 */

public class TreeTest {
	
	
	
	@Test
	public void directTraversal() {
		Tree<String> tree = new Tree<String>("A");
		tree.addLeft("B").addLeft("D").addRight("G");
		Node<String> c = tree.getRoot().addRight("C");
		c.addRight("F");
		Node<String> e = c.addLeft("E");
		e.addLeft("H");
		e.addRight("I");
		
		String[] expected=new String[]{"A","B","D","G","C","E","H","I","F"};
		int i=0;
		Iterator<String> iterator = tree.iterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			System.out.print(next);
			assertEquals(expected[i], next);
			i++;
		}
		
		
	}
	@Test
	public void symmetricTraversal() {
		Tree<String> tree = new Tree<String>("A");
		tree.addLeft("B").addLeft("D").addRight("G");
		Node<String> c = tree.getRoot().addRight("C");
		c.addRight("F");
		Node<String> e = c.addLeft("E");
		e.addLeft("H");
		e.addRight("I");
		String[] expected=new String[]{"D","G","B","A","H","E","I","C","F"};
		int i=0;
		Iterator<String> iterator = tree.symetricIterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			System.out.print(next);
			assertEquals(expected[i], next);
			i++;
		}
		
		
	}
	@Test
	public void reverseTraversal() {
		Tree<String> tree = new Tree<String>("A");
		tree.addLeft("B").addLeft("D").addRight("G");
		Node<String> c = tree.getRoot().addRight("C");
		c.addRight("F");
		Node<String> e = c.addLeft("E");
		e.addLeft("H");
		e.addRight("I");
		String[] expected=new String[]{"G","D","B","H","I","E","F","C","A"};
		int i=0;
		Iterator<String> iterator = tree.reverseIterator();
		while (iterator.hasNext()){
			String next = iterator.next();
			System.out.print(next);
			assertEquals(expected[i], next);
			i++;
		}
		
		
	}
	@Test(expected=NullPointerException.class)
	public void testExc(){
		Tree<String> tree = new Tree<String>("A");
		tree.throwException();
	}
	@Test
	public void testCheckedExc(){
		Tree<String> tree = new Tree<String>("A");
		try {
			tree.throwCheckedException();
			fail(String.format("Exception %s was not thrown!!!",Exception.class.getName()));
		} catch (Exception e) {
		//All is OK
		}
	}
}
