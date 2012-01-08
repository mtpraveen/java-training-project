package by.epam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.dao.BlogRepository;
import by.epam.blog.dao.UserRepository;
import by.epam.blog.model.Blog;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 * 
 */
@Service
@Transactional
public class BlogServiceImpl {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository userRepository;

	public Iterable<Blog> findAllBlogs() {
		return blogRepository.findAll();
	}

	public Blog findBlogById(Long blog) {
		return blogRepository.findOne(blog);
	}

	public Long saveBlog(Blog blog) {
		return blogRepository.save(blog).getId();
	}
	public Long rename(Blog blog, String name) {
		blog.setName(name);
		return blogRepository.save(blog).getId();
	}
	public Blog findBlogByLogin(String login, String pass) {
		Iterable<User> test = userRepository.findAll();
		Blog blog = new Blog();
		for (User s : test) {
			// System.out.println(s.getLogin());
			if (s.getLogin().equalsIgnoreCase(login)) {
				blog = s.getBlog();
				// System.out.println("if"+s.getLogin());
			}
		}
		// System.out.println(blog.getName());
		return blog;
	}
}
