package by.brsu.java.training.model;

import java.util.List;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.User;

public interface IBlogModel {
	boolean registerUser(User user);
	boolean unregisterUser(long userId);
	User getUser(long userId);
	List<User> getUsers();
	void editUser(User user);
	boolean isModerator(long userId);
	void setModerator(long userId);
	
	boolean createBlog(long userId, Blog blog);
	boolean deleteBlog(long blogId);
	Blog getBlog(long blogId);
	List<Blog> getUserBlogs(long userId);
	
	boolean createArticle(long blogId, Article article);
	List<Article> getBlogArticles(long idBlog);
	Article getArticle(long articleId);
	boolean editArticle(Article article);
	boolean deleteArticle(long articleId);
	void publishArticle(long articleId);
//	void 
	
	boolean addComment(Comment comment);
	void publishComment(long commentId);
	
	

	
}
