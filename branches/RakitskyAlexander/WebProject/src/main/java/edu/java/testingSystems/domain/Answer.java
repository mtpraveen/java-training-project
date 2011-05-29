/**
 * 
 */
package edu.java.testingSystems.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class entity stores user answers
 * 
 * @author Rakitsky Alexander
 * @param <b>id</b> - autocomplete field of id
 * @param <b>user</b> - user who enters their answers
 * @param <b>language</b> - programming language that the user enters
 * @param <b>level</b> - level that exposes itself to the user
 * @see edu.java.testingSystems.domain.Answer#id
 * @see edu.java.testingSystems.domain.Answer#user
 * @see edu.java.testingSystems.domain.Answer#language
 * @see edu.java.testingSystems.domain.Answer#level
 */
@Entity
@Table(name = "TESTING")
public class Answer {
	/**
	 * autocomplete field of id
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	/**
	 * user who enters their answers
	 */
	@Column(name = "USER")
	private String user;
	/**
	 * programming language that the user enters
	 */
	@Column(name = "LANGUAGE")
	private String language;
	/**
	 * level that exposes itself to the user
	 */
	@Column(name = "LEVEL")
	private String level;

	/**
	 * @return a Integer the <b>id</b> - autocomplete field of id
	 * @see java.lang.Integer
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * the id to set
	 * 
	 * @param <b>id</b> the id to set
	 * @see edu.java.testingSystems.domain.Answer#id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return a String the <b>user</b> - user who enters their answers
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Answer#id
	 */
	public String getUser() {
		return user;
	}

	/**
	 * the user to set
	 * 
	 * @param <b>user<b> types a String
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Answer#user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the <b>language</b> types a String
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Answer#user
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * the language to set
	 * 
	 * @param language
	 *            types a String
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Answer#language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * get level that exposes itself to the user
	 * 
	 * @return level types a String
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Answer#language
	 */
	public String getLevel() {
		return level;
	}

	/**
	 * to the level to set
	 * 
	 * @param <b>level</b> level that exposes itself
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Answer#level
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/**
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
		Answer other = (Answer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [id=" + id + ", user=" + user + ", language=" + language
				+ ", level=" + level + "]";
	}

}
