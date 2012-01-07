package by.epam.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.epam.blog.service.BlogServiceImpl;

/**
 * @author Dmitry_Goncharov
 *
 */

@Controller
public class BlogController {
	@Autowired
	private BlogServiceImpl blog_service;
	
	@RequestMapping(value = "/spisok")
	public String user(Model model) {
		model.addAttribute("blog", blog_service.findAllBlogs());
		return "spisok";
	}
	
	@RequestMapping(value = "/blog/{blogId}")
	public String blog(@PathVariable("blogId") long blogId, Model model) {
		//model.addAttribute("topics", blog_service.listTopicBlogById(blogId));
		model.addAttribute("topics", blog_service.findBlogById(blogId).getTopics());
		
		model.addAttribute("t", blog_service.findBlogById(blogId));
		
		return "blog";
	}
}
