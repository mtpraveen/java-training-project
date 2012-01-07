package by.epam.blog.dao;

import java.util.Collection;

import by.epam.blog.model.Blog;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
public interface BlogsRepositoryOld {
	Blog createBlog(String subject, User user);
	Blog findBlogById(long id);
	Collection<Blog> findAllBlogs();
	void deleteBlog(long id);
}
