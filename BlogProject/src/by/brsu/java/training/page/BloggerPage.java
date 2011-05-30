package by.brsu.java.training.page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Label;
import org.apache.click.control.PageLink;
import org.apache.click.extras.control.HtmlFieldSet;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.service.UserService;
import by.brsu.java.training.util.DateUtil;
import by.brsu.java.training.util.UserInfo;

public class BloggerPage extends HomePage {

	List<HtmlFieldSet> blogsList;
	private String nameString = "";
	private String dateString = "";
	private String aboutString = "";
	private String bloggerMessage = "";
	private String blogsMessage = "";
	User user;
	private int blogsCount;

	private List<HtmlFieldSet> createBlogsList() {

		blogsList = new ArrayList<HtmlFieldSet>();
		TreeSet<Blog> blogs = new TreeSet<Blog>(user.getBlogs());
		for (Blog blog : blogs) {
			HtmlFieldSet htmlFieldSet = new HtmlFieldSet("blog");
			htmlFieldSet.setShowBorder(false);
			
			String blogLinkStr = String.format(
			"<a href=blog-page.htm?%s=%s>%s</a>", BLOG_ID, blog.getId(), blog.getTitle());
			Label blogLink = new Label("blogLink");
			blogLink.setLabel(blogLinkStr);
			htmlFieldSet.add(blogLink);
			
			Label date = new Label("date");
			date.setLabel(DateUtil.dateFormat(blog.getDate()));
			htmlFieldSet.add(date);
			Label articlesCount = new Label("articlesCount");
			articlesCount.setLabel(String.format("%d", blog.getArticles().size()));
			htmlFieldSet.add(articlesCount);
			blogsList.add(htmlFieldSet);
		}
		blogsCount = blogsList.size();
		return blogsList;
	}
	
	private User getUser() {
		String name = (String) getContext().getRequestParameter(BLOGGER_NAME);
		if (name == null) {
			setRedirect("/bloggers-page.htm");
			return null;
		}
		user = UserService.getInstance().getUserByName(name);
		return user;
	}

	private String createBloggerMessage() {
		bloggerMessage = String.format("Page of user ", user.getName());
		return bloggerMessage;
	}
	
	private String createBlogsMessage() {
		if (user.getBlogs() == null) {
			blogsMessage = "there are no blogs";
		}
		if (user.getBlogs().size() == 0) {
			blogsMessage = "there are no blogs";
		}
		return blogsMessage;
	}

	private String createNameString() {
		if (user != null) {
			nameString = user.getName();
		}
		return nameString;
	}

	private String createDateString() {
		if (user == null) {
			return dateString;
		}
		dateString = DateUtil.dateFormat(user.getDate());
		return dateString;
	}

	private String createAboutString() {
		if (user == null) {
			return aboutString;
		}
		aboutString = user.getAbout();
		return aboutString;
	}

	public BloggerPage() {
		getUser();
		if (user == null) {
			setRedirect("/bloggers-page.htm");
		} else {
			addModel("bloggerMessage", bloggerMessage);
			addModel("blogsMessage", createBlogsMessage());
			addModel("nameString", createNameString());
			addModel("dateString", createDateString());
			addModel("aboutString", createAboutString());
			addModel("blogsList", createBlogsList());
			addModel("blogsCount", blogsCount);
		}
	}
}