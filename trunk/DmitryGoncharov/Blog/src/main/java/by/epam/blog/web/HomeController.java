package by.epam.blog.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import by.epam.blog.dao.GoBlogsRepository;
import by.epam.blog.model.Blog;
import by.epam.blog.service.UserServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private GoBlogsRepository goBlogsRepository = new GoBlogsRepository();
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("blog",goBlogsRepository.findAllBlogs());
		return "home";
	}
	
	@RequestMapping(value = "/test")
	public String test(Model model) {
		int test = 10;
		model.addAttribute("test", test);
		return "test";
	}

	@RequestMapping(value = "/blog/{blogId}")
	public String blog(@PathVariable("blogId") long blogId, Model model) {
		Blog blog = goBlogsRepository.findBlogById(blogId);
		model.addAttribute("author", blog.getAuthor().getName());
		model.addAttribute("topics",blog.getTopics());
		return "blog";
	}
	
}