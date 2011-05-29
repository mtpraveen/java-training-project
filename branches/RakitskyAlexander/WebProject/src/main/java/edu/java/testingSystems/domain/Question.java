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
 * class entity to work with programming languages (add, delete)
 * 
 * @author Rakitsky Alexander
 * @param id
 *            - identification number of questions in the table
 * @param language
 *            - programming language is stored in a database
 * @see edu.java.testingSystems.domain.Question#id
 * @see edu.java.testingSystems.domain.Question#language
 */
@Entity
@Table(name = "QUESTION")
public class Question {
	/**
	 * <b>id</b> - identification number of questions in the table
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;
	/**
	 * <b>language</b> - programming language is stored in a database
	 */
	@Column(name = "LANGUAGE")
	private String language;

	/**
	 * getting id number
	 * 
	 * @return the id types a Integer
	 * @see java.lang.Integer
	 * @see edu.java.testingSystems.domain.Question#id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * setting id number
	 * 
	 * @param id
	 *            the id to set types a Integer
	 * @see java.lang.Integer
	 * @see edu.java.testingSystems.domain.Question#id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * getting programming language
	 * 
	 * @return <b>language</b> types a String
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Question#language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * setting programming language
	 * 
	 * @param language
	 *            types a String
	 * @see java.lang.String
	 * @see edu.java.testingSystems.domain.Question#language
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Question [id=" + id + ", language=" + language + "]";
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
		Question other = (Question) obj;
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
		return true;
	}

}
