package by.brsu.java.training.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.util.HibernateUtil;

public class UserDB implements IUserDAO {

	public Session session;

	private boolean isBlogExists(long id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Blog.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		session.getTransaction().commit();
		return list.size() > 0;
	}

	private boolean isUserExists(long id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		session.getTransaction().commit();
		return list.size() > 0;
	}

	private boolean isArticleExists(long id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Article.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		session.getTransaction().commit();
		return list.size() > 0;
	}

	public boolean addUser(User user) {
		if (isUserExists(user.getId())) {
			return false;
		}
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		return true;
	}

	public User getUserById(long id) {
		if (!isUserExists(id)) {
			return null;
		}
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User) session.load(User.class, id);
		return user;
	}

	public User getUserByName(String name) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("name", name));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return null;
		}
		User user = (User) crit.list().get(0);
		return user;
	}

	public Set<User> getUsers() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(User.class);
		Set<User> users = new TreeSet<User>(crit.list());
		return users;
	}

	public boolean deleteUser(long id) {
		if (!isUserExists(id)) {
			return false;
		}

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return false;
		}
		User user = (User) crit.list().get(0);
		session.delete(user);
		session.getTransaction().commit();
		return true;
	}

	public void commit() {
		if (session != null) {
			if (session.isOpen()) {
				if (session.getTransaction() != null) {
					if (session.getTransaction().isActive()) {
						session.getTransaction().commit();
					}
				}
			}
		}
	}

	public boolean addBlog(long userId, Blog blog) {
		if (!isUserExists(userId)) {
			return false;
		}
		if (isBlogExists(blog.getId())) {
			return false;
		}
		User user = getUserById(userId);

		for (Blog curBlog : user.getBlogs()) {
			if (curBlog.getTitle().equalsIgnoreCase(blog.getTitle())) {
				return false;
			}
		}
		user.getBlogs().add(blog);
		commit();
		return true;
	}

	public boolean deleteBlog(long blogId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Blog.class);
		crit.add(Restrictions.idEq(blogId));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return false;
		}
		Blog blog = (Blog) crit.list().get(0);
		session.delete(blog);
		session.getTransaction().commit();
		return true;
	}

	public Blog getBlogById(long blogId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Blog.class);
		crit.add(Restrictions.idEq(blogId));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return null;
		}
		Blog blog = (Blog) crit.list().get(0);
		return blog;
	}

	public Blog getBlogByTitle(long userId, String blogTitle) {
		User user = getUserById(userId);
		for (Blog blog : user.getBlogs()) {
			if (blog.getTitle().equalsIgnoreCase(blogTitle))
				return blog;
		}
		commit();
		return null;
	}

	public boolean addArticle(long blogId, Article article) {
		if (!isBlogExists(blogId)) {
			return false;
		}
		if (isArticleExists(article.getId())) {
			return false;
		}
		Blog blog = getBlogById(blogId);

		for (Article curArticle : blog.getArticles()) {
			if (curArticle.getTitle().equalsIgnoreCase(article.getTitle())) {
				commit();
				return false;
			}
		}
		blog.getArticles().add(article);
		commit();
		return true;
	}

	public boolean addComment(long articleId, Comment comment) {
		Article article = getArticleById(articleId);
		if (article == null) {
			return false;
		}
		article.getComments().add(comment);
		commit();
		return true;
	}

	public boolean deleteArticle(long articleId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Article.class);
		crit.add(Restrictions.idEq(articleId));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return false;
		}
		Article article = (Article) crit.list().get(0);
		session.delete(article);
		session.getTransaction().commit();
		return true;
	}

	public boolean deleteComment(long commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	public Article getArticleById(long articleId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Article.class);
		crit.add(Restrictions.idEq(articleId));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return null;
		}
		Article article = (Article) crit.list().get(0);
		return article;
	}

	public Article getArticleByTitle(long blogId, String title) {
		Blog blog = getBlogById(blogId);
		if (blog == null) {
			return null;
		}
		for (Article article : blog.getArticles()) {
			if (article.getTitle().equalsIgnoreCase(title))
				return article;
		}
		commit();
		return null;
	}

	public Comment getCommentById(long commentId) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Comment.class);
		crit.add(Restrictions.idEq(commentId));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return null;
		}
		Comment comment = (Comment) crit.list().get(0);
		return comment;
	}

	public boolean setTags(long articleId, Set<Tag> tags) {
		Article article = getArticleById(articleId);
		if (article == null) {
			commit();
			return false;
		}
		article.setTags(tags);
		commit();
		return true;
	}

}
