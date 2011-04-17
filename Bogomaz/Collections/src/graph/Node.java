package graph;

public class Node<W> implements INode{
	private String name;//Name of node
	private W weight;//Weight of node
	private static int counterForName = 0;
	
	public Node(){
		this("", null);
		name = "N" + counterForName;
	}	
	
	public Node(W weight){
		this("", weight);
		name = "N" + counterForName;
	}
	
	public Node(String name, W weight){
		this.name = name;
		this.weight = weight;
		counterForName++;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	public W getWeight() {
		return weight;
	}

	public void setWeight(W weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
}
