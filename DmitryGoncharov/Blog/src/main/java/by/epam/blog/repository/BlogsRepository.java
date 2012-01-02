package by.epam.blog.repository;

import java.util.Collection;

import by.epam.blog.model.Blog;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
public interface BlogsRepository {
	Blog createBlog(String subject, User user);
	Blog findBlogById(long id);
	Collection<Blog> findAllBlogs();
	void deleteBlog(long id);
}
