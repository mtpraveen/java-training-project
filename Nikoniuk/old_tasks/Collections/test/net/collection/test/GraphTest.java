package net.collection.test;

import java.util.Iterator;

import static junit.framework.Assert.*;

import net.collections.Graph;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author epam0001
 *
 */
public class GraphTest {

	private Graph<String> g;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		g = new Graph<String>(6);
		int [][] links = new int[][]{{0, 1},{1, 3}, {0, 3}, {0, 2}, {2, 4}, {2, 5}};
		String [] data = new String[]{"a", "b", "c", "d", "e", "f"};
		for(int i = 0; i < links.length; ++i) {
			g.addLink(links[i][0], links[i][1]);
			g.setNodeData(i, data[i]);
		}
		g.setStartPos(0);
		g.setEndPos(3);
	}
	
	@Test
	public void depthTraversalIterator() {
		Iterator<String> it = g.iterator();
		String [] data = new String[]{"a", "b", "d", "c", "e", "f"};
		
		int i = 0;
		while(it.hasNext()) {
			assertEquals(it.next(), data[i++]);
		}
	}
	
	@Test
	public void shortestPathIterator() {
		Iterator<String> it = g.shortestPathIterator();
		String [] data = new String[]{"a", "d"};
		
		int i = 0;
		while(it.hasNext()) {
			assertEquals(it.next(), data[i++]);
		}
	}
	
	@Test
	public void shortestPathIteratorRemove() {
		Iterator<String> it = g.shortestPathIterator();
		
		while(it.hasNext()) {
			if (it.next() == "d") {
				it.remove();
			}
		}
		
		it = g.shortestPathIterator();
		
		assertFalse(it.hasNext());

	}
	
	@Test
	public void depthTraversalIteratorRemove() {
		Iterator<String> it = g.iterator();
		
		while(it.hasNext()) {
			String next = it.next();
			if (next == "d") {
				it.remove();
			}
		}
		
		it = g.iterator();
		
		String [] data = new String[]{"a", "b", "c", "e", "f"};
		
		int i = 0;
		while(it.hasNext()) {
			assertEquals(it.next(), data[i++]);
		}
	}

}
