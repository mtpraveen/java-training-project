package by.brsu.java.training.dao;

import java.util.Set;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;

public interface IUserDAO {
	User getUserById(long id);
	User getUserByName(String name);
	Set<User> getUsers();
	boolean addUser(User user);
	boolean deleteUser(long id);
	void commit();
	
	boolean addBlog(long userId, Blog blog);
	Blog getBlogById(long blogId);
	Blog getBlogByTitle(long userId, String blogTitle);
	boolean deleteBlog(long blogId);
	
	boolean addArticle(long blogId, Article article);
	Article getArticleById(long articleId);
	Article getArticleByTitle(long blogId, String title);
	boolean deleteArticle(long articleId);
	
	boolean addComment(long articleId, Comment comment);
	Comment getCommentById(long commentId);
	boolean deleteComment(long commentId);
	
	boolean setTags(long articleId, Set<Tag> tags);
}
