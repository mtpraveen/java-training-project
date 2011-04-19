package autobase.model;

/**
 * <p>Types of requests in the system</p>
 * @see Request
 * @author Alexander Nikoniuk
 * 
 */
public enum RequestType {
    RACE,
    REPAIR;
    
    public static RequestType typeById(int id) {
    	for (RequestType type: RequestType.values()) {
    		if (type.ordinal() == id) 
    			return type;
    	}
    	return null;
    }

}
