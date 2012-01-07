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

import by.epam.blog.model.User;
import by.epam.blog.service.UserServiceImpl;

/**
 * @author Dmitry_Goncharov
 *
 */

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl service;
	
	@RequestMapping(value = "/user")
	public String user(Model model) {
		model.addAttribute("users", service.findAllUsers());
		return "user";
	}
	
	@RequestMapping(value = "/new")
	public ModelAndView showForm(){
		return new ModelAndView("forma", "command", new User());
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createOrder(@ModelAttribute User user, BindingResult result, Model model) {
		System.out.println(user.getLogin() + "/" + user.getPass()+"/"+user.getName());
		/*
		validate(order, result);
		if (result.hasErrors()){
			model.addAttribute("command", order);
			return "order";
		}
		*/
		return "redirect:showuser/"+service.saveUser(user);
	}
		
	@RequestMapping(value = "/showuser/{userId}")
	public String createOrder(@PathVariable(value="userId") Long user, Model model){
		model.addAttribute("user", service.findOrderById(user));
		return "showuser";
	}
}