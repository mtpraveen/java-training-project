/**
 * Trainig Sample project
 * Admin
 */
package by.epam.pretender.model;

import java.util.Date;

/**
 * @author Admin
 *
 */
public class Comment {
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	private String text;
	private Date date;
}
