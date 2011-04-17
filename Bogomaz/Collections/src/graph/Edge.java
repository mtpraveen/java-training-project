package graph;

public class Edge<W> implements IEdge{
	private INode begin;//Start of edge
	private INode end;//End of edge
	private W weight;//Weight of edge
	private String name;//Name of edge
	private static int counterForName = 0;
	
	public Edge(INode begin, INode end){
		this(begin, end, "", null);
		this.name = begin.getName() + ":" + "E" +  counterForName + ":" + end.getName();
	}
	
	public Edge(INode begin, INode end, W weight){
		this(begin, end, "", weight);
		this.name = begin.getName() + ":" + "E" + counterForName + ":" + end.getName();
	}
	
	public Edge(INode begin, INode end, String name, W weight){
		if (begin == null || end == null)
			throw new NullPointerException("Both begin node and end node must be determined for an edge!");
		this.begin = begin;
		this.end = end;
		this.name = name;
		this.weight = weight;
		counterForName++;
	}
	
	@Override
	public INode getBegin() {
		return begin;
	}
	
	@Override
	public void setBegin(INode begin) {
		if (begin == null)
			throw new NullPointerException("Begin node must be determined for an edge!");
		this.begin = begin;
	}
	
	@Override
	public INode getEnd() {
		return end;
	}
	
	@Override
	public void setEnd(INode end) {
		if (end == null)
			throw new NullPointerException("End node must be determined for an edge!");
		this.end = end;
	}

	public W getWeight() {
		return weight;
	}

	public void setWeight(W weight) {
		this.weight = weight;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public Edge<W> reverseEdge(){
		Edge<W> edge= new Edge<W>(end, begin, weight);
		return edge;
	}
	
	public Edge<W> reverseEdge(W weight){
		Edge<W> edge = reverseEdge();
		edge.setWeight(weight);
		return edge;
	}
}
