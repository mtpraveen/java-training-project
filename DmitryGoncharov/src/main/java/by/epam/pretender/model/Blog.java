/**
 * Trainig Sample project
 * Admin
 */
package by.epam.pretender.model;

import java.util.List;

/**
 * @author Admin
 *
 */
public class Blog {
	private User author;
	private List<Article> articles;

	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
