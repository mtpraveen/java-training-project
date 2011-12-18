/**
 * Trainig Sample project
 * Admin
 */
package by.epam.blog.dao;

import java.util.Collection;
import java.util.HashMap;
import by.epam.blog.model.Blog;
import by.epam.blog.model.Topic;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
public class GoBlogsRepository implements BlogsRepository {
	private HashMap<Long, Blog> blogs;
	/* (non-Javadoc)
	 * @see by.epam.pretender.dao.BlogsRepository#createBlog()
	 */
	public GoBlogsRepository(){
		init();
	}
	private void init(){
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		user1.setName("Dmitry");
		user2.setName("Maksim");
		user3.setName("Daniil");
		blogs=new HashMap<Long, Blog>();
		Blog blog1 = new Blog();
		Blog blog2 = new Blog();
		Blog blog3 = new Blog();

		blog1.setAuthor(user1);
		blog1.getTopics().add(new Topic("Статья первая"));
		blog1.getTopics().add(new Topic("Статья вторая"));
		
		blog2.setAuthor(user2);
		blog2.getTopics().add(new Topic("Про максима"));
		blog2.getTopics().add(new Topic("Про Java"));
		blog2.getTopics().add(new Topic("Пумпурум"));
		
		blog3.getTopics().add(new Topic("Данилка"));
		blog3.setAuthor(user3);
		
		
		blogs.put(1L, blog1);
		blogs.put(2L, blog2);
		blogs.put(3L, blog3);
	}
	
	public Blog createBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see by.epam.pretender.dao.BlogsRepository#findBlogById(int)
	 */
	public Blog findBlogById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Blog createBlog(String subject, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Blog> findAllBlogs() {
		// TODO Auto-generated method stub
		return blogs.values();
	}

	public void deleteBlog(int id) {
		// TODO Auto-generated method stub
		
	}
}
