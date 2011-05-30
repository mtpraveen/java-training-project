package by.brsu.java.training.util;

import by.brsu.java.training.entity.Blog;

public class BlogUtil {
	public static Blog copyBlog(Blog blog) {
		if (blog == null) {
			return null;
		}
		Blog blogCopy = new Blog(blog.getId(), blog.getTitle(), blog
				.getAuthor(), blog.getDate(), blog.getArticles(), blog
				.getUser());
		return blogCopy;
	}

}
