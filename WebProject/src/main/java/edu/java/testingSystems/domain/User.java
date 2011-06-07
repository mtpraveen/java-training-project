/**
 * 
 */
package edu.java.testingSystems.domain;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * essence - the user
 * @author Rakitsky Alexander
 * @param name - username 
 */
public class User {
	private String name;

	/**
	 * get username
	 * @return the name types a String
	 * @see java.lang.Integer
	 * @see edu.java.testingSystems.domain.User#name
	 */
	public String getName() {
		return name;
	}

	/**
	 * the username to set
	 * @param name types a String
	 * @see java.lang.Integer
	 *  @see edu.java.testingSystems.domain.User#name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * static method - find the current user
	 * @return username current user
	 * @see org.springframework.security.core.context.SecurityContextHolder
	 */
	public static String getUserNowName()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**)
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
	
}
