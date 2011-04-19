package net.collections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph<T> implements Iterable<T>{
	private int [][] graph;
	private ArrayList<T> data;
	private int curPos;
	private int startPos;
	private int endPos;
	
	private int size;
	private LinkedList<Integer> visitedNodes = new LinkedList<Integer>();
	private LinkedList<Integer> minPath = new LinkedList<Integer>();
	
	public Graph(int size) {
		if (size <= 0)
			throw new RuntimeException("The size should be greater than 0");
		this.size = size;
		graph = new int[size][size];
		data = new ArrayList<T>();
		for (int i = 0; i < size; ++i)
			data.add(null);
	}

	public void addLink(int i, int j) {
		if (i >= size || j >= size)
			throw new IllegalArgumentException();
		graph[i][j] = 1;
	}

	public void removeLink(int i, int j) {
		if (i >= size || j >= size)
			throw new IllegalArgumentException();
		graph[i][j] = 0;
	}

	/**
	 * @param i the index of the node to set the data
	 * @param value the value to set
	 */
	public void setNodeData(int i, T value) {
		if (i >= size)
			throw new IllegalArgumentException();
		data.set(i, value);
	}

	/**
	 * @param pos the startPos to set
	 */
	public void setStartPos(int pos) {
		this.startPos = pos;
	}

	/**
	 * @param pos the endPos to set
	 */
	public void setEndPos(int pos) {
		this.endPos = pos;
	}

	/**
	 * @return size of the graph
	 */
	public int getSize() {
		return size;
	}
	
	public Iterator<T> shortestPathIterator() {
		return new Iterator<T>() {
			{
				minPath.clear();
				findShortestPath(startPos, endPos, 0);
			}
			
			@Override
			public boolean hasNext() {
				return minPath.size() != 0;
			}

			@Override
			public T next() {
				curPos = minPath.pollFirst();
				return data.get(curPos);
			}

			@Override
			public void remove() {
				excludeNode(curPos);	
				
			}
			
			@SuppressWarnings("unchecked")
			private void findShortestPath(int pos, int aim, int steps) {
				visitedNodes.add(pos);
				if (pos == aim && (steps < minPath.size() || minPath.size() == 0)) {
					minPath = (LinkedList<Integer>) visitedNodes.clone();
				}

				if (steps < size) {
					for (int i = 0; i < size; ++i)
						if(graph[pos][i] == 1 && !visitedNodes.contains(i) && pos != i)
							findShortestPath(i, aim, steps + 1);
				}
				visitedNodes.pollLast();
			}		
		};
	}
	
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			{
				visitedNodes.clear();
			}

			@Override
			public boolean hasNext() {
				if (canMove())
					return true;
				else
					return false;
			}

			private boolean canMove() {
				if (visitedNodes.size() == 0 && size != 0) 
					return true;
				
				for(int i = 0; i < visitedNodes.size(); i++) {
					int pos = visitedNodes.get(i);
					for (int j = 0; j < graph.length; ++j)
						if (graph[pos][j] == 1 && !visitedNodes.contains(j) && curPos != j)
							return true;
				}
				return false;
			}

			@Override
			public T next() {
				if (visitedNodes.size() == 0 && size != 0) {
					visitedNodes.add(startPos);
					curPos = startPos;
					return data.get(curPos);
				}
				
				for (int i = 0; i < graph.length; ++i) {
					if (graph[curPos][i] == 1 && !visitedNodes.contains(i) && i != curPos) {
						visitedNodes.add(i);
						curPos = i;
						return data.get(curPos);
					}
				}
				if (canMove()) {
					try {
						curPos = visitedNodes.get(visitedNodes.indexOf(curPos) - 1);
					} catch (IndexOutOfBoundsException e) {
						curPos = visitedNodes.get(visitedNodes.size() - 1);
					}
					return next();
				}
				else {
					return null;
				}
			}

			@Override
			public void remove() {
				excludeNode(curPos);		
			}
		};
	}
	
	protected void excludeNode(int pos) {
		if (size == 0)
			throw new RuntimeException("Cant remove element from empty graph");
		for (int j = 0; j < size; ++j)
			removeLink(j, pos);	
		int i = visitedNodes.indexOf(pos);
		if (i != -1)
			visitedNodes.remove(i);
		setNodeData(pos, null);
		size--;
		
	}

	public static void main(String []args) {
		try {
			Scanner input = new Scanner(System.in); 
			
			System.out.println("Input size: ");	
			int size = input.nextInt();
			Graph<String> g = new Graph<String>(size);
			
			System.out.println("Adjacency matrix example: \n0 1 1 0\n1 1 1 0\n1 1 0 1\n0 1 0 1\n");
			System.out.println("Input adjacency matrix: ");
			
			for (int i = 0; i < g.getSize(); i++) {
				for (int j = 0; j < g.getSize(); j++)
				{
					if (input.nextInt() == 1)
						g.addLink(i, j);
				}
			}
			
			System.out.println("Input nodes data: ");
			for (int i = 0; i < g.getSize(); i++) {
				System.out.println(i + ": ");
				System.in.skip(System.in.available());
				g.setNodeData(i, input.next());
			}
			
			System.out.println("Input start and end positions: ");
			g.setStartPos(input.nextInt());
			g.setEndPos(input.nextInt());
		
			
			Iterator<String> it = g.shortestPathIterator();
			//Iterator<String> it = g.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}
		catch(RuntimeException e) {
			System.err.println("Error. " + e.getMessage());
		}
		catch(IOException e) {
			System.err.println("Error. " + e.getMessage());
		}
	}
}
