/**
 * 
 */
package edu.java.testingSystem.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.java.testingSystems.domain.User;

/**
 * class system controller
 * 
 * @author Rakitsky Alexander
 * @see edu.java.question.service.QuestionService
 * @see edu.java.answers.service.AnswersService
 */
@Controller
public class ControllerSistem {

	/**
	 * Front page. Available to all users
	 * 
	 * @param map
	 * @return home.jsp - see src/main/webapp/WEB-INF/views/home.jsp
	 */
	@RequestMapping("/home")
	public String homePage(Map<String, Object> map) {
	User user = new User();
	user.setName(User.getUserNowName());
	map.put("userNow", user);
	return "index";
	}

	/**
	 * Startup is redirected to the start page
	 * 
	 * @see edu.java.testingSystem.web.ControllerSistem#homePage(Map)
	 */
	@RequestMapping("/")
	public String home() {
		return "redirect:/home";
	}

	/**
	 * login page user
	 * 
	 * @return login.jsp - see src/main/webapp/WEB-INF/views/login.jsp
	 */
	@RequestMapping("/login")
	public String showAnswers() {
		return "login";
	}

	/**
	 * error page
	 * 
	 * @return error.jsp - see src/main/webapp/WEB-INF/views/error.jsp
	 */
	@RequestMapping("/error")
	public String errot() {
		return "error";
	}
//	@RequestMapping("/home")
//	public String deleteme() {
//		
//	}
}
