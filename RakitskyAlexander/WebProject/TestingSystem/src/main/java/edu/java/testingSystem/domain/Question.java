/**
 * 
 */
package edu.java.testingSystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * class entity to work with programming languages (add, delete)
 * @author Rakitsky Alexander
 * @param id - identification number of questions in the table
 * @param languge - programming language is stored in a database
 */
@Entity
@Table(name = "QUESTION")
public class Question {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	@Column(name = "LANGUAGE")
	private String language;

	/**
	 * getting id number
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * setting id number
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * getting programming language
	 * @return the programming language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * setting programming language
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	
}
