package by.epam.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.epam.blog.model.Blog;
import by.epam.blog.model.User;
import by.epam.blog.service.BlogServiceImpl;
import by.epam.blog.service.UserServiceImpl;

/**
 * @author Dmitry_Goncharov
 * 
 */

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private BlogServiceImpl blogService;

	@RequestMapping(value = "/user")
	public String user(Model model) {
		model.addAttribute("users", userService.findAllUsers());
		return "user";
	}

	@RequestMapping(value = "/reguser")
	public ModelAndView showForm() {
		return new ModelAndView("reguser", "command", new User());
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createOrder(@ModelAttribute User user, BindingResult result, Model model) {
		user.setBlog(blogService.findBlogById(blogService.saveBlog(new Blog("MyBlog"))));
		System.out.println(user.getLogin() + "/" + user.getPass() + "/"+ user.getName());
		return "redirect:showuser/" + userService.saveUser(user);
	}

	@RequestMapping(value = "/showuser/{userId}")
	public String createOrder(@PathVariable(value = "userId") Long user, Model model) {
		model.addAttribute("user", userService.findUserById(user));
		model.addAttribute("blog_name", userService.findUserById(user)
				.getBlog().getName());
		return "showuser";
	}
}