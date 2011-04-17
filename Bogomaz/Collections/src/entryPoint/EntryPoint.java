package entryPoint;

import graph.*;

/**
 * ������� �� ���� ������� � �����������
	1.	����������� �����, �������������� ����� ��������������� ����. 
	����� ������ ������������� ��������� Collection<E> (��������� ������)
	� ������ ����� ��������� ������, ����������� ����������� ��� �����������
	����������� � ������. ���� �� ������ ��������� ������ � ������������ ���.

	2.	����������� ���� �� ����������:
		2.1.	����� � �������.
		2.2.	����� � ������.
		2.3.	�������� ������ ��� ���������� ����������� ���� ����� ������ ������.
		2.4.	�������� �������� ��� ������ ����������� ����.
 */

public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start.");
		System.out.println("\nWork results of floyd algorithm:");
		OrientedGraph<Node<String>, Edge<Double>> g = new OrientedGraph<Node<String>, Edge<Double>>();
		Node<String> n1 = new Node<String>("W1");
		Node<String> n2 = new Node<String>("W2");
		Node<String> n3 = new Node<String>("W3");
		Node<String> n4 = new Node<String>("W4");
		Edge<Double> e1 = new Edge<Double>(n1, n2, 1.0);
		Edge<Double> e2 = new Edge<Double>(n2, n3, 2.0);
		Edge<Double> e3 = new Edge<Double>(n3, n1, 3.0);
		Edge<Double> e4 = e1.reverseEdge(4.0);
		Edge<Double> e5 = e2.reverseEdge(5.0);
		Edge<Double> e6 = e3.reverseEdge(6.0);
		System.out.println("Empty graph:");
		System.out.println(g.printFloydAlgorithmResult(g.floydAlgorithm(g)));
		System.out.println("Graph with 1 node:");
		g.add(n1);
		System.out.println(g.printFloydAlgorithmResult(g.floydAlgorithm(g)));
		System.out.println("Coherent graph with 2 nodes and 1 edge between them:");
		g.add(n2);
		g.addEdge(e1);
		System.out.println(g.printFloydAlgorithmResult(g.floydAlgorithm(g)));
		System.out.println("Coherent graph with 2 nodes and 2 edges between them:");
		g.addEdge(e4);
		System.out.println(g.printFloydAlgorithmResult(g.floydAlgorithm(g)));
		System.out.println("Coherent graph with 3 nodes and 6 edges between them:");
		g.add(n3);
		g.addEdge(e2);
		g.addEdge(e3);
		g.addEdge(e5);
		g.addEdge(e6);
		System.out.println(g.printFloydAlgorithmResult(g.floydAlgorithm(g)));
		System.out.println("Incoherent graph with 4 nodes and 6 edges between 3 nodes:");
		g.add(n4);
		System.out.println(g.printFloydAlgorithmResult(g.floydAlgorithm(g)));
		System.out.println("Oriented graph:");
		System.out.println(g.printGraph());
		System.out.println("End.");
		
	}
}
