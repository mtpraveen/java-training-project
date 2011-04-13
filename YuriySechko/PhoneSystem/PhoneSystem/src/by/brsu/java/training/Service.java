package by.brsu.java.training;

/**
 * Class for modeling service in phone system
 * @author yura
 * 
 */
public class Service {
	private static int nextId = 1;
    private static int getNextId(){
        return nextId++;
    }
	
	private int id;
	private String name;
	private int cost;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param id
	 * @param cost
	 * @param name
	 */
	public Service(String name, int cost) {
		super();
		this.id = getNextId();
		this.cost = cost;
		this.name = name;
	}


	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Service [id=" + id + ", cost=" + cost + ", " + "name=" + name + "]";
	}
}
