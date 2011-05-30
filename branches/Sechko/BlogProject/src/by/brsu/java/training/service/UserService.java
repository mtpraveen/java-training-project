package by.brsu.java.training.service;

import java.util.Set;
import java.util.TreeSet;

import by.brsu.java.training.dao.IUserDAO;
import by.brsu.java.training.dao.UserDB;
import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.util.ArticleUtil;
import by.brsu.java.training.util.BlogUtil;
import by.brsu.java.training.util.UserInfo;
import by.brsu.java.training.util.UserUtil;

public class UserService implements IUserService {

	private static final UserService INSTANCE = new UserService();
	private IUserDAO userDAO = null;

	private UserService() {
	}

	public static UserService getInstance() {
		return INSTANCE;
	}

	private static IUserDAO getUserDAO() {
		if (INSTANCE.userDAO == null) {
			INSTANCE.userDAO = new UserDB();
		}
		return INSTANCE.userDAO;
	}

	public boolean addUser(User user) {
		user.setId(0);
		UserService.getUserDAO().addUser(user);
		return true;
	}

	public boolean isUserExists(String name) {
		User user = getUserDAO().getUserByName(name);
		getUserDAO().commit();
		return user != null;
	}

	public User getUserById(long id) {
		User userCopy = UserUtil.copyUser(getUserDAO().getUserById(id));
		getUserDAO().commit();
		return userCopy;
	}

	public User getUserByName(String name) {
		User userCopy = UserUtil.copyUser(getUserDAO().getUserByName(name));
		if (userCopy != null) {
			getUserDAO().commit();
		}
		return userCopy;
	}

	public Set<User> getUsers() {
		Set<User> usersCopy = UserUtil.copyUsers(getUserDAO().getUsers());
		getUserDAO().commit();
		return usersCopy;
	}

	public Set<UserInfo> getUserInfo() {
		Set<UserInfo> usersInfo = new TreeSet<UserInfo>();
		Set<User> users = getUserDAO().getUsers();
		for (User user : users) {
			usersInfo.add(new UserInfo(user.getName(), user.getDate()));
		}
		getUserDAO().commit();
		return usersInfo;
	}

	public Blog getBlogById(long id) {
		Blog blogCopy = BlogUtil.copyBlog(getUserDAO().getBlogById(id));
		if (blogCopy != null) {
			getUserDAO().commit();
		}
		return blogCopy;
	}

	public Article getArticleById(long id) {
		Article article = getUserDAO().getArticleById(id);
		Article articleCopy = ArticleUtil.copyArticle(article);
		if (article != null) {
			getUserDAO().commit();
		}
		return articleCopy;
	}

	public boolean deleteBlog(long blogId) {
		return getUserDAO().deleteBlog(blogId);
	}

	public boolean deleteArticle(long articleId) {
		return getUserDAO().deleteArticle(articleId);
	}

	public boolean isBlogExists(long userId, String blogTitle) {
		if (getUserDAO().getBlogByTitle(userId, blogTitle) != null) {
			getUserDAO().commit();
			return true;
		}
		return false;
	}

	public boolean addBlog(long userId, Blog blog) {
		return getUserDAO().addBlog(userId, blog);
	}

	public boolean addComment(long articleId, Comment comment) {
		return getUserDAO().addComment(articleId, comment);
	}

	public Article getArticleByTitle(long blogId, String articleTitle) {
		Article articleCopy = ArticleUtil.copyArticle(getUserDAO()
				.getArticleByTitle(blogId, articleTitle));
		if (articleCopy != null) {
			getUserDAO().commit();
		}
		return articleCopy;
	}

	public boolean addArticle(long blogId, Article article) {
		return getUserDAO().addArticle(blogId, article);
	}

	public Blog getBlogByTitle(long userId, String blogTitle) {
		Blog blogCopy = BlogUtil.copyBlog(getUserDAO().getBlogByTitle(userId,
				blogTitle));
		if (blogCopy != null) {
			getUserDAO().commit();
		}
		return blogCopy;
	}

	public boolean setTags(long articleId, Set<Tag> tags) {
		return getUserDAO().setTags(articleId, tags);
	}

	public boolean editArticle(Article article) {
		Article oldArticle = getUserDAO().getArticleById(article.getId());
		if (oldArticle == null || article == null) {
			return false;
		}
		oldArticle.setDate(article.getDate());
		oldArticle.setText(article.getText());
		if (article.getTags() != null) {
			oldArticle.setTags(article.getTags());
		}
		getUserDAO().commit();
		return true;
	}

}
