package by.epam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.dao.BlogRepository;
import by.epam.blog.model.Blog;
import by.epam.blog.model.Topic;

/**
 * @author Dmitry_Goncharov
 *
 */
@Service
@Transactional
public class BlogServiceImpl {
	 @Autowired
	 private BlogRepository blogRepository;
	
	 public Iterable<Blog> findAllBlogs() {
			return 	blogRepository.findAll();
	}
	public Blog findBlogById(Long blog) {
			return blogRepository.findOne(blog);
	}
	public Iterable<Topic> listTopicBlogById(Long blog) {
		return blogRepository.findOne(blog).getTopics();
	}
}
