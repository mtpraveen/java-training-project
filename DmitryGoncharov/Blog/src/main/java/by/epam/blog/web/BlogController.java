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
	private BlogServiceImpl blogService;
	
	@RequestMapping(value = "/spisok")
	public String user(Model model) {
		model.addAttribute("blog", blogService.findAllBlogs());
		return "spisok";
	}
	
	@RequestMapping(value = "/blog/{blogId}")
	public String blog(@PathVariable("blogId") long blogId, Model model) {
		model.addAttribute("topics", blogService.findBlogById(blogId).getTopics());
		model.addAttribute("user", blogService.findBlogById(blogId).getUser().getName());
		return "blog";
	}
}
