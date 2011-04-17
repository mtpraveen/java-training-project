package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//This graph is an oriented graph without loops and parallel (directed to same node) edges
public class OrientedGraph<N extends INode, E extends IEdge> implements Collection<N>{
	
	public static final boolean HAS_LOOPS = false;
	public static final boolean HAS_PARALLEL_EDGES = false;
	private Set<E> edges;
	private Map<N, Map<N, E>> adjacentList;//if node N is a single node, then Map<N, E> has null value
	
	public OrientedGraph(){
		adjacentList = new HashMap<N, Map<N,E>>();
		edges = new HashSet<E>();
	}
	
	//Nodes methods:
	//Collection methods:
	@Override
	public boolean add(N node) {
		if (node == null)
			return false;
		if (adjacentList.containsKey(node))
			return false;
		else{
			adjacentList.put(node, null);
			return true;
		}
	}
	@Override
	public boolean addAll(Collection<? extends N> nodes) {
		boolean result = true;
		if (nodes == null)
			return false;
		for(N node : nodes)
			if (!add(node))
				result = false;
		return result;
	}
	@Override
	public void clear() {
		adjacentList.clear();
	}
	@Override
	public boolean contains(Object node) {
		return adjacentList.containsKey(node);
	}
	@Override
	public boolean containsAll(Collection<?> nodes) {
		if (nodes == null)
			return false;
		for (Object node : nodes)
			if (!contains(node))
				return false;
		return true;
	}
	@Override
	public boolean isEmpty() {
		return adjacentList.isEmpty();
	}
	@Override
	public Iterator<N> iterator() {
		return adjacentList.keySet().iterator();
	}
	@Override
	public boolean remove(Object node) {
		if (adjacentList.containsKey(node)){
			if (adjacentList.get(node) != null)
				for (E edge :adjacentList.get(node).values())
					edges.remove(edge);
			adjacentList.remove(node);
			return true;
		}
		else
			return false;
	}
	@Override
	public boolean removeAll(Collection<?> nodes) {
		if (nodes == null)
			return false;
		boolean result = false;
		for (Object node : nodes)
			if (remove(node))
				result = true;
		return result;
	}
	@Override
	public boolean retainAll(Collection<?> nodes) {
		if (nodes == null)
			return false;
		boolean result = false;
		for (N node : adjacentList.keySet())
			if (!nodes.contains(node)){
				remove(node);
				result = true;
			}
		return result;
	}
	@Override
	public int size() {
		return adjacentList.size();
	}
	@Override
	public Object[] toArray() {
		return adjacentList.keySet().toArray();
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		return adjacentList.keySet().toArray(arg0);
	}
	//Oriented graph methods:
	//return any node of oriented graph
	public N aNode(){
		if (size() == 0)//there are not any nodes in graph
			return null;
		List<N> temp = new ArrayList<N>(adjacentList.keySet());
		return temp.get(0);
	}
	public int inDegree(N node){
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException("There is not such node in graph!");
		int degree = 0;
		for (N n : adjacentList.keySet())
			if (adjacentList.get(n) != null && adjacentList.get(n).containsKey(node))
				degree++;
		return degree;
	}
	public int outDegree(N node){
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException("There is not such node in graph!");
		if (adjacentList.get(node) == null)//single node
			return 0;
		return adjacentList.get(node).size();
	}
	
	public Set<E> inEdges(N node){
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException("There is not such node in graph!");
		Set<E> temp = new HashSet<E>();
		for (E edge : edges)
			if (edge.getEnd() == node)
				temp.add(edge);
		if (temp.size() == 0)
			return null;
		else
			return temp;
	}
	
	public Set<E> outEdges(N node){
		if (!adjacentList.containsKey(node))
			throw new IllegalArgumentException("There is not such node in graph!");
		if (adjacentList.get(node) == null)//single node
			return null;
		Set<E> temp = new HashSet<E>(adjacentList.get(node).values());
		return temp;
	}
	
	//Edges methods:
	//Collection methods:
	
	///Edges methods:
	//Collection methods:
	public boolean addEdge(E edge){
		if (edge == null)
			return false;
		if (edges.contains(edge))//such edge is already existing
			return false;
		if (edge.getBegin() == null || edge.getEnd() == null)//check for edge without nodes
			return false;
		if (edge.getBegin() == edge.getEnd())//check for loops
			return false;
		for (E e : edges)//check for parallel edges
			if ( (e.getBegin()==edge.getBegin() && e.getEnd()==edge.getEnd()) )
				return false;
		Map<N, E> temp;
		if (adjacentList.get(edge.getBegin()) == null){
			temp = new HashMap<N, E>();
			adjacentList.put((N)edge.getBegin(), temp);
		}else
			temp = adjacentList.get(edge.getBegin());
		temp.put((N)edge.getEnd(), edge);
		edges.add(edge);
		return true;
	}
	
	public boolean addAllEdges(Collection<E> edges){
		if (edges == null)
			return false;
		boolean result = true;
		for (E edge : edges)
			if (!addEdge(edge))
				result = false;
		return result;
	}
	
	public void clearEdges(){
		edges.clear();
		for (N node : adjacentList.keySet())
			adjacentList.put(node, null);
	}
	
	public boolean containsEdge(E edge){
		return edges.contains(edge);
	}
	
	public boolean containsEdge(N begin, N end){
		if (adjacentList.get(begin) != null && adjacentList.get(begin).containsKey(end))
			return true;
		else
			return false;
	}
	
	public boolean containsAllEdges(Collection<E> edges){
		return this.edges.containsAll(edges);
	}
	
	public boolean isEdgesEmpty(){
		return edges.isEmpty();
	}
	
	public Iterator<E> edgesIterator(){
		return edges.iterator();
	}
	
	public boolean removeEdge(E edge){
		if (!edges.contains(edge))//there is not such edge in graph
			return false;
		adjacentList.get(edge.getBegin()).remove(edge.getEnd());
		if (adjacentList.get(edge.getBegin()).size() == 0)
			adjacentList.put((N)edge.getBegin(), null);
		edges.remove(edge);
		return true;
	}
	
	public boolean removeAllEdges(Collection<E> edges){
		if (edges == null)
			return false;
		boolean result = false;
		for (E edge : edges)
			if (removeEdge(edge))
				result = true;
		return result;
	}
	
	public boolean retainAllEdges(Collection<E> edges){
		if (edges == null)
			return false;
		boolean result = false;
		for (E edge : this.edges)
			if (!edges.contains(edge)){
				result = removeEdge(edge);
				result = true;
			}
		return result;
	}
		
	public int edgesSize(){
		return edges.size();
	}
	
	public Object toEdgesArray(){
		return edges.toArray();
	}
	
	public <T> T[] toEdgesArray(T[] arg0){
		return edges.toArray(arg0);
	}

	//Oriented graph methods:
	//return any edge of oriented graph
	//Oriented graph methods:
	//return any edge of oriented graph
	public E anEdge(){
		if (edges.size() == 0)//there are not any edges in graph
			return null;
		List<E> temp = new ArrayList<E>(edges);
		return temp.get(0);
	}
	
	public boolean reverseDirection(E edge){
		if (!edges.contains(edge))//there is not such edge in graph
			return false;
		adjacentList.get(edge.getBegin()).remove(edge.getEnd());
		INode temp = edge.getBegin();
		edge.setBegin(edge.getEnd());
		edge.setEnd(temp);
		adjacentList.get(edge.getBegin()).put((N)edge.getEnd(), edge);
		return true;
	}
	
	public boolean setDirectionFrom(E edge, N node){
		if (!edges.contains(edge))//there is not such edge in graph
			return false;
		if (edge.getBegin() == node)//such edge is already existing
			return false;
		if (edge.getEnd() == node)//check for loops
			return false;
		for (E e : edges)//check for parallel edges
			if ( (e.getBegin()==node && e.getEnd()==edge.getEnd()) )
				return false;
		adjacentList.get(edge.getBegin()).remove(edge.getEnd());
		edge.setBegin(node);
		adjacentList.get(edge.getBegin()).put((N)edge.getEnd(), edge);
		return true;
			
	}
	public boolean setDirectionTo(E edge, N node){
		if (!edges.contains(edge))//there is not such edge in graph
			return false;
		if (edge.getEnd() == node)//such edge is already existing
			return false;
		if (edge.getBegin() == node)//check for loops
			return false;
		for (E e : edges)//check for parallel edges
			if ( (e.getBegin()==edge.getBegin() && e.getEnd()==node) )
				return false;
		adjacentList.get(edge.getBegin()).remove(edge.getEnd());
		edge.setEnd(node);
		adjacentList.get(edge.getBegin()).put((N)edge.getEnd(), edge);
		return true;
	}
	
	//Weights of edges for graph in this method must be >= 0
	//If there is not route in graph between two nodes, then corresponding List<Edge<Double>> has null value
	public <S> Map<Node<S>, Map<Node<S>, List<Edge<Double>>>> floydAlgorithm(OrientedGraph<Node<S>, Edge<Double>> graph) throws ClassCastException, NullPointerException{
		//Ordering all nodes in graph
		List<Node<S>> temp = new ArrayList<Node<S>>((Set<Node<S>>)graph.adjacentList.keySet());
		int n = temp.size();
		if (n == 0) return null;
		Double[][] a = new Double[n][n];
		Integer[][] p = new Integer[n][n];
		//Implementation Floyd's algorithm according to book "Aho, Hopkroft "Data structures and algorithms""
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++){
				if ( graph.adjacentList.get(temp.get(i)) != null && graph.adjacentList.get(temp.get(i)).containsKey(temp.get(j)) )
					if (graph.adjacentList.get(temp.get(i)).get(temp.get(j)).getWeight() != null)
						a[i][j] = graph.adjacentList.get(temp.get(i)).get(temp.get(j)).getWeight();
					else
						throw new NullPointerException("The weight of some edge has null-value!");
				else
					a[i][j] = Double.POSITIVE_INFINITY;
				p[i][j] = -1;
			}
		for (int i = 0; i < n; i++)
			a[i][i] = 0.0;
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (a[i][k] + a[k][j] < a[i][j]){
						a[i][j] = a[i][k] + a[k][j];
						p[i][j] = k;
					}
		//Organization method return
		List<Integer> route = new ArrayList<Integer>();
		List<Edge<Double>> tempList = new ArrayList<Edge<Double>>();
		Map<Node<S>, List<Edge<Double>>> tempMap = new HashMap<Node<S>, List<Edge<Double>>>();
		Map<Node<S>, Map<Node<S>, List<Edge<Double>>>> routes = new HashMap<Node<S>, Map<Node<S>, List<Edge<Double>>>>();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				path(i, j, p, route);
				int k;
				if (route.size() > 0){
					k = route.get(0);
					tempList.add((Edge<Double>)graph.adjacentList.get(temp.get(i)).get(temp.get(k)));
					for (int r = 0; r < route.size() - 1; r++)
						tempList.add((Edge<Double>)graph.adjacentList.get(temp.get(route.get(r))).get(temp.get(route.get(r+1))));
					k = route.get(route.size() - 1);
				}else
					k = i;
				if ( graph.adjacentList.get(temp.get(k)) != null && graph.adjacentList.get(temp.get(k)).get(temp.get(j)) != null )
					tempList.add((Edge<Double>)graph.adjacentList.get(temp.get(k)).get(temp.get(j)));
				else					
					tempList = null;
				tempMap.put(temp.get(j), tempList);
				routes.put(temp.get(i), tempMap);
				route.clear();
				tempList = new ArrayList<Edge<Double>>();
			}
			tempMap = new HashMap<Node<S>, List<Edge<Double>>>();
		}
		return routes;
	}
	//auxiliary method for floydAlgorithm method
	private void path(Integer i, Integer j, Integer[][] p, List<Integer> route){
		Integer k = p[i][j];
		if (k == -1)
			return;
		path(i, k, p, route);
		route.add(k);
		path(k, j, p, route);
	}
	//method for printing paths between all nodes 
	public String printFloydAlgorithmResult(Map<N, Map<N, List<E>>> routes){
		StringBuilder result = new StringBuilder("");
		if (routes == null)
			return "empty\n";
		for (N node1 : routes.keySet()){
			for (N node2 : routes.get(node1).keySet()){
				result.append(node1.getName());
				result.append(":->");
				if (routes.get(node1).get(node2) != null){
					for (E edge : routes.get(node1).get(node2)){
						result.append(edge.getName());
						result.append("->");
					}
				}
				else
					result.append("null->");
				result.append(":");
				result.append(node2.getName());
				result.append("\n");
			}
		}
		return result.toString();
	}

	public String printGraph(){
		StringBuilder result = new StringBuilder("");
		if (size() == 0)
			return "empty\n";
		for (N node1 : adjacentList.keySet()){
			if (adjacentList.get(node1) != null){
				for (N node2 : adjacentList.get(node1).keySet()){
					result.append(node1.getName());
					result.append(":->");
					result.append(adjacentList.get(node1).get(node2).getName());
					result.append("->:");
					result.append(node2.getName());
					result.append("\n");
				}
			}
			else{
				result.append(node1.getName());
				result.append("\n");
			}
		}
		return result.toString();
	}
}
