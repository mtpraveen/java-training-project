package by.brsu.java.training.service;

import java.util.Set;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.util.UserInfo;

public interface IUserService {
	boolean isUserExists(String name);
	boolean addUser(User user);
	Set<UserInfo> getUserInfo();
	User getUserByName(String name);
	Blog getBlogById(long id);
	Article getArticleById(long id);
	boolean deleteBlog(long blogId);
	boolean deleteArticle(long articleId);
	boolean addBlog(long userId, Blog blog);
	boolean addComment(long articleId, Comment comment);
	Article getArticleByTitle(long blogId, String articleTitle);
	boolean addArticle(long blogId, Article article);
	Blog getBlogByTitle(long userId, String blogTitle);
	boolean setTags(long articleId, Set<Tag> tags);
	boolean editArticle(Article article);
}
