package by.brsu.java.training.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionButton;
import org.apache.click.control.Label;
import org.apache.click.control.PageLink;
import org.apache.click.extras.control.HtmlFieldSet;
import org.apache.click.extras.control.PageButton;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.service.UserService;
import by.brsu.java.training.util.ArticleUtil;
import by.brsu.java.training.util.DateUtil;

public class BlogPage extends HomePage {

	public static final int SHORT_ARTICLE_LENGTH = 350;
	
	private static final long serialVersionUID = -3621970253569943920L;
	private Blog blog;
	private ActionButton deleteBlogButton;
	private String articlesMessage = "";
	private HtmlFieldSet blogSummary;
	List<HtmlFieldSet> articlesList;
	private int articlesCount;
	

	private Blog getBlog() {
		long blogId = 0;
		Object requestParam = getContext().getRequestParameter(BLOG_ID);
		Object sessionParam = getContext().getSessionAttribute(BLOG_ID);
		if (requestParam == null && sessionParam == null) {
			setRedirect("/bloggers-page.htm");
			return null;
		}
		if (requestParam != null) {
			blogId = Long.parseLong((String) getContext().getRequestParameter(
					BLOG_ID));
		} else if (sessionParam != null) {
			blogId = (Long)getContext().getSessionAttribute(
					BLOG_ID);
		}
		blog = UserService.getInstance().getBlogById(blogId);
		if (blog != null) {
			getContext().setSessionAttribute(BLOG_ID, blogId);
		} else {
			setRedirect("/bloggers-page.htm");
			return null;
		}
		return blog;
	}
	
	private List<HtmlFieldSet> createArticlesList() {

		articlesList = new ArrayList<HtmlFieldSet>();
		TreeSet<Article> articles = new TreeSet<Article>(blog.getArticles());
		for (Article article : articles) {
			// if (article.isPublished()) {
			HtmlFieldSet htmlFieldSet = new HtmlFieldSet("article");
			htmlFieldSet.setShowBorder(false);
				String authorLink = String.format(
					"<a href=blogger-page.htm?%s=%s>%s</a>", BLOGGER_NAME, article
							.getAuthor(), article.getAuthor());
			Label author = new Label("author");
			author.setLabel(authorLink);
			htmlFieldSet.add(author);
			String titleLink = String.format("<a href=article-page.htm?%s=%d>%s</a>", ARTICLE_ID, article.getId(), article.getTitle());
			Label title = new Label("title");
			title.setLabel(titleLink);
			htmlFieldSet.add(title);
			Label date = new Label("date");
			date.setLabel(DateUtil.dateFormat(article.getDate()));
			htmlFieldSet.add(date);
			Label tags = new Label("tags");
			String tagsString = ArticleUtil.createTagString(article.getTags());
			tags.setLabel(tagsString);
			Label text = new Label("text");
			int endIndex = 0;
			if ( article.getText().length() < SHORT_ARTICLE_LENGTH) { 
				endIndex = article.getText().length();
			} else {
				endIndex = SHORT_ARTICLE_LENGTH;
			}
			text.setLabel(article.getText().substring(0, endIndex).concat("..."));
			htmlFieldSet.add(text);
			articlesList.add(htmlFieldSet);
			// }
		}
		articlesCount = articlesList.size();
		return articlesList;
	}

	private String createArticlesMessage() {
		if (blog.getArticles() == null) {
			articlesMessage = "there are no articles";
		}
		if (blog.getArticles().size() == 0 || articlesList.size() == 0) {
			articlesMessage = "there are no articles";
		}
		return articlesMessage;
	}

	private HtmlFieldSet createBlogSummary() {
		blogSummary = new HtmlFieldSet("blogSummary");
		Label blogTitle = new Label("blogTitle");
		blogTitle.setLabel(blog.getTitle());
		blogSummary.add(blogTitle);
		
		Label blogDate = new Label("blogDate");
		blogDate.setLabel(DateUtil.dateFormat(blog.getDate()));
		blogSummary.add(blogDate);
		return blogSummary;
	}

	private ActionButton createDeleteBlogButton() {
		deleteBlogButton = null;
		if (isUserAutorized() && isUserHasBlog(blog)) {
			deleteBlogButton = new ActionButton("deleteBlogButton");
			deleteBlogButton.setLabel("Delete this blog");
			deleteBlogButton.setActionListener(new ActionListener() {
				private static final long serialVersionUID = -7194396338279431137L;

				public boolean onAction(Control source) {

					if (isUserAutorized() && isUserHasBlog(blog)) {
						UserService.getInstance().deleteBlog(blog.getId());
						String name = (String) getContext()
								.getSessionAttribute(USERNAME_ATTRIBUTE);
						String link = String.format("/blogger-page.htm?%s=%s",
								BLOGGER_NAME, name);
						getContext().setSessionAttribute(BLOG_ID, null);
						setRedirect(link);
					}
					return false;
				}
			});

		}
		return deleteBlogButton;
	}

	/**
	 * 
	 */
	public BlogPage() {
		if (getBlog() == null) {
		} else {
			addModel("articlesList", createArticlesList());
			addModel("articlesMessage", createArticlesMessage());
			addControl(createBlogSummary());
			if (createDeleteBlogButton() != null) {
				addControl(deleteBlogButton);
			}
		}
	}

}