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
 * @author Rakitsky Alexander
 *
 */
@Entity
@Table(name="TESTING")
public class Testing {
	@Id
	@Column(name="ID")
	@GeneratedValue
	private Integer id;
	
	@Column(name="USER")
	private String user;
	
	@Column(name="LANGUAGES")
	private String languages;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the languages
	 */
	public String getLanguages() {
		return languages;
	}
	/**
	 * @param languages the languages to set
	 */
	public void setLanguages(String languages) {
		this.languages = languages;
	}
	
	
}
