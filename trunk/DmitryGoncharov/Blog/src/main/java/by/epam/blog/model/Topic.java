/**
 * Trainig Sample project
 * Admin
 */
package by.epam.blog.model;

import java.util.List;

/**
 * @author DmitryGoncharov
 *
 */

public class Topic {
	private String caption;
	private String text;
	private List<String> tags;
	private List<Comment> comments;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
