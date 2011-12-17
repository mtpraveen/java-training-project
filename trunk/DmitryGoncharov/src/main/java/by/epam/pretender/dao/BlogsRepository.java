/**
 * Trainig Sample project
 * Admin
 */
package by.epam.pretender.dao;

import java.util.Collection;

import by.epam.pretender.model.Blog;
import by.epam.pretender.model.User;

/**
 * @author Admin
 *
 */
public interface BlogsRepository {
	Blog createBlog(String subject, User user);
	Blog findBlogById(int id);
	Collection<Blog> findAllBlogs();
	void deleteBlog(int id);
}
