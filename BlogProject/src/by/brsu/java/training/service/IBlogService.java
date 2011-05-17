package by.brsu.java.training.service;

import java.util.List;

import by.brsu.java.training.entity.Blog;

public interface IBlogService {
	boolean addBlog(long userId, Blog blog);
	Blog getBlogById(long blogId);
	List<Blog> getUserBlogs(long userId);
	boolean deleteBlog(long blogId);
}
