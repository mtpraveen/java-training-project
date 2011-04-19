package autobase.model;

/**
 * <p>Types of users in the system</p>
 * @see User
 * @see Admin
 * @see Dispatcher
 * @see Driver
 * 
 * @author Alexander Nikoniuk
 * 
 */
public enum UserType {
    DRIVER,
    DISPATCHER,
    ADMIN,
    GUEST;
    
    public static UserType typeById(int id) {
    	for (UserType type: UserType.values()) {
    		if (type.ordinal() == id) 
    			return type;
    	}
    	return null;
    }

}
