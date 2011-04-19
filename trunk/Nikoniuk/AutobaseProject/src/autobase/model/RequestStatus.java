package autobase.model;

/**
 * <p>Request statuses</p>
 * @see Request
 * @author Alexander Nikoniuk
 * 
 */
public enum RequestStatus {
    COMPLETED,
    ACTIVE,
    CANCELED;
    
    public static RequestStatus typeById(int id) {
    	for (RequestStatus type: RequestStatus.values()) {
    		if (type.ordinal() == id) 
    			return type;
    	}
    	return null;
    }
}
