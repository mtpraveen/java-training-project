package net.epam.java.autobase;

public class User implements IUser {
    private String username;
    private String password;
    private boolean suspended;
    private int id;
    protected static Autobase server = Autobase.getInstance();

    /**
	 * @param username
	 * @param passwordHash
	 */
	public User(int id, String username, String password) {
		this.username = username;
		this.password = password;
		this.id = id;
		this.suspended = false;
	}
	public User() { }
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the passwordHash
	 */
	public String getPassword() {
		return password;
	}

	@Override
	public void changePassword(int sid, String password) {
		server.changePassword(sid, password);
	}
	@Override
	public void setSuspended(int sid, boolean value) {
		server.setSuspended(sid, value);		
	}
	

	public void setSuspended(boolean value) {
		suspended = value;		
	}	
	
	/**
	 * @return the suspended
	 */
	public boolean isSuspended() {
		return suspended;
	}
	public void changePassword(String password) {
		this.password = password;		
	}
	
	@Override
	public int logIn(String username, String password) throws AuthorizationException {
		return server.logIn(username, password);
	}
	
	public static UserType checkPermissions(int sid) {
		return server.checkPermissions(sid);
	}	
	
	@Override
	public void logOut(int sid) {
		server.logOut(sid);
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
		return username + "(suspended=" + suspended
				+ ", id=" + id + ")";
	}
	
	

}
