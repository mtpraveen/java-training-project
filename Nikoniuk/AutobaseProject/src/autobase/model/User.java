package autobase.model;

import autobase.exceptions.AuthorizationException;
import autobase.server.Autobase;

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
		return username + "( id=" + id + ", suspended=" + suspended + ")";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + (suspended ? 1231 : 1237);
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (suspended != other.suspended)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
