package by.brsu.java.training.page;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionButton;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;

public class HomePage extends BorderedPage {
	private static final String BUTTON_WIDTH = "180";
	public static final String BLOGGER_NAME = "bloggerName";
	public static final String BLOG_ID = "blogId";
	public static final String ARTICLE_ID = "articleId";

	private ActionButton bloggersButton;
	private ActionButton allArticlesButton;
	private ActionButton myBlogsButton;

	private ActionButton createBlogButton;
	private ActionButton createArticleButton;
	private ActionButton latestArticlesButton;
	private ActionButton latestCommentsButton;
	private ActionButton logoutButton;

	/* (non-Javadoc)
	 * @see org.apache.click.Page#onInit()
	 */
	@Override
	public void onInit() {
		// TODO Auto-generated method stub
		super.onInit();
	}

	protected boolean isUserHasBlog(Blog blog) {
		String username = (String) getContext().getSessionAttribute(
				USERNAME_ATTRIBUTE);
		return blog.getAuthor().equalsIgnoreCase(username);
	}

	protected boolean isUserHasArticle(Article article) {
		String username = (String) getContext().getSessionAttribute(
				USERNAME_ATTRIBUTE);
		return article.getAuthor().equalsIgnoreCase(username);
	}

	private ActionButton createBloggersButton() {
		bloggersButton = new ActionButton("bloggersButton");
		bloggersButton.setLabel("Bloggers");
		bloggersButton.setWidth(BUTTON_WIDTH);
		bloggersButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/bloggers-page.htm");
				return false;
			}
		});
		return bloggersButton;
	}

	private ActionButton createAllArticlesButton() {
		allArticlesButton = new ActionButton("allArticlesButton");
		allArticlesButton.setLabel("Articles");
		allArticlesButton.setWidth(BUTTON_WIDTH);
		allArticlesButton.setActionListener(new ActionListener() {
			public boolean onAction(Control source) {
				setRedirect("/all-articles-page.htm");
				return false;
			}
		});
		return allArticlesButton;
	}

	private ActionButton createMyBlogsButton() {
		myBlogsButton = new ActionButton("myBlogsButton");
		myBlogsButton.setLabel("My blogs");
		myBlogsButton.setWidth(BUTTON_WIDTH);
		myBlogsButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (getUserRole() == BLOGGER_ROLE
						|| getUserRole() == MODERATOR_ROLE) {
					String name = (String) getContext().getSessionAttribute(
							USERNAME_ATTRIBUTE);
					setRedirect(String.format("/blogger-page.htm?%s=%s",
							BLOGGER_NAME, name));
				}
				return false;
			}
		});
		return myBlogsButton;
	}

	private ActionButton createCreateBlogButton() {
		createBlogButton = new ActionButton("createBlogButton");
		createBlogButton.setLabel("Create blog");
		createBlogButton.setWidth(BUTTON_WIDTH);
		createBlogButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/create-blog-page.htm");
				return false;
			}
		});
		return createBlogButton;

	}

	private ActionButton createCreateArticleButton() {
		createArticleButton = new ActionButton("createArticleButton");
		createArticleButton.setLabel("Create article");
		createArticleButton.setWidth(BUTTON_WIDTH);
		createArticleButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/create-article-page.htm");
				return false;
			}
		});
		return createArticleButton;
	}

	private ActionButton createLatestArticlesButton() {
		latestArticlesButton = new ActionButton("latestArticlesButton");
		latestArticlesButton.setLabel("Latest articles");
		latestArticlesButton.setWidth(BUTTON_WIDTH);
		latestArticlesButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/latest-articles-page.htm");
				return false;
			}
		});
		return latestArticlesButton;
	}

	private ActionButton createLatestCommentsButton() {
		latestCommentsButton = new ActionButton("latestCommentsButton");
		latestCommentsButton.setLabel("Latest comments");
		latestCommentsButton.setWidth(BUTTON_WIDTH);
		latestCommentsButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/latest-comments-page.htm");
				return false;
			}
		});
		return latestCommentsButton;
	}

	private ActionButton createLogoutButton() {
		logoutButton = new ActionButton("logoutButton");
		logoutButton.setLabel("Log out");
		logoutButton.setWidth(BUTTON_WIDTH);
		logoutButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				invalidateUser();
				setRedirect("/bloggers-page.htm");
				return false;
			}
		});
		return logoutButton;
	}
	
	public HomePage() {
		addControl(createBloggersButton());
		addControl(createAllArticlesButton());
		createLogoutButton();
		if (getUserRole() == UserRole.BLOGGER) {
			addControl(createMyBlogsButton());
			addControl(createCreateBlogButton());
			addControl(createCreateArticleButton());
			addControl(logoutButton);
		}

		if (getUserRole() == UserRole.MODERATOR) {
			addControl(createMyBlogsButton());
			addControl(createCreateBlogButton());
			addControl(createCreateArticleButton());
			addControl(createLatestArticlesButton());
			addControl(createLatestCommentsButton());
			addControl(logoutButton);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see by.brsu.java.training.page.BorderedPage#getTemplate()
	 */
	@Override
	public String getTemplate() {
		// TODO Auto-generated method stub
		return "/home.htm";
	}

}