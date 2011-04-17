package graph;

public interface IEdge{
	String getName();
	void setName(String name);
	INode getBegin();
	void setBegin(INode begin);
	INode getEnd();
	void setEnd(INode end);
}
