/**
 * 
 */
package edu.java.testingSystem.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.java.answers.service.AnswersService;
import edu.java.question.service.QuestionService;
import edu.java.testingSystems.domain.Answer;
import edu.java.testingSystems.domain.Question;
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
	private Answer answer;
	/**
	 * Autocomplete field settings are in folder src / main / webapp / WEB-INF /
	 * spring / data.xml
	 */
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswersService testingService;

	/**
	 * Adding and deleting questions. This page is available only to the
	 * administrator.
	 * 
	 * @param map
	 * @return admin.jsp - see src/main/webapp/WEB-INF/views/admin.jsp
	 */
	@RequestMapping("/admin")
	public String listQuestion(Map<String, Object> map) {
		map.put("question", new Question());
		map.put("questionList", questionService.listQuestion());
		return "admin";
	}

	/**
	 * Displays all responses from the database. Ability to remove questions.
	 * Available only to the administrator.
	 * 
	 * @param map
	 * @return user.jsp - see src/main/webapp/WEB-INF/views/allAnswers.jsp
	 */
	@RequestMapping("/allAnswers")
	public String listTests(Map<String, Object> map) {
		map.put("answer", new Answer());
		map.put("listAnswer", testingService.listAnswers());
		return "allAnswers";
	}

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
		return "home";
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
	 * Adding a question is only available to the administrator to redirect
	 * admin.jsp
	 * @see edu.java.testingSystem.web.ControllerSistem#listQuestion(Map)
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question,
			BindingResult result) {
		questionService.addQuestion(question);
		return "redirect:/admin";
	}

	/**
	 * Delete a question is only available to the administrator to redirect
	 * admin.jsp
	 * @see edu.java.testingSystem.web.ControllerSistem#listQuestion(Map)
	 */
	@RequestMapping("/deleteQuestion/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId) {
		questionService.removeQuestion(contactId);
		return "redirect:/admin";
	}
	/**
	 * Page where registered users to post their answers
	 * @return addAnswers.jsp -see src/main/webapp/WEB-INF/views/addAnswers.jsp
	 */
	@RequestMapping("/addAnswers")
	public String addUserView(Map<String, Object> map) {
		User user = new User();
		user.setName(User.getUserNowName());
		map.put("userNow", user);
		map.put("questions", questionService.listQuestion());
		map.put("testList", testingService.listAnswerUserNow());
		map.put("testing", new Answer());
		return "addAnswers";
	}
	/**
	 * Adding a answer is only available to the registered users to redirect addAnswers.jsp
	 *  @see edu.java.testingSystem.web.ControllerSistem#addUserView(Map)
	 */
	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public String addTest(@ModelAttribute("answer") Answer answer,
			BindingResult result) {
		answer.setUser(User.getUserNowName());
		testingService.addAnswer(answer);
		return "redirect:/addAnswers";
	}
	/**
	 * Delete a answer is only available to the registered users to redirect showAnswers.jsp
	 *  @see edu.java.testingSystem.web.ControllerSistem#showAnswers()
	 */
	@RequestMapping("/deleteMyAnswer/{testId}")
	public String deleteMyAnswer(@PathVariable("testId") Integer testId) {
		testingService.removeAnswer(testId);
		return "redirect:/showAnswers";
	}
	/**
	 * Delete a answer is only available to the manager to redirect allAnswer.jsp
	 *  @see edu.java.testingSystem.web.ControllerSistem#showAnswers()
	 */
	@RequestMapping("/deleteAnswer/{testId}")
	public String deleteAnswer(@PathVariable("testId") Integer testId) {
		testingService.removeAnswer(testId);
		return "redirect:/allAnswers";
	}
	/**
	 * Page where the user can see their responses
	 * @return showAnswer.jsp - see src/main/webapp/WEB-INF/views/showAnswers.jsp
	 */
	@RequestMapping("/showAnswers")
	public String showAnswers(Map<String, Object> map) {
		map.put("testList", testingService.listAnswerUserNow());
		map.put("testing", new Answer());
		return "showAnswers";
	}
	/**
	 * Page where the manager can find the answers
	 * @return manager.jsp - see src/main/webapp/WEB-INF/views/manager.jsp
	 */
	@RequestMapping("/manager")
	public String manager(Map<String, Object> map) {
		Answer answers = new Answer();
		map.put("testing", answers);
		if (answer != null) {
			String language = answer.getLanguage();
			String level = answer.getLevel();
			if (language != null && level != null) {
				map.put("answersLang",
						testingService.listFindLanguage(language, level));
			}
			answer = null;
		}
		return "manager";
	}
	/**
	 * Search for answers in terms and language. Is redirected to manager.jsp
	 * @see edu.java.testingSystem.web.ControllerSistem#manager(Map)
	 */
	@RequestMapping(value = "/setAnswers", method = RequestMethod.POST)
	public String setAnswers(@ModelAttribute("testing") Answer test,
			BindingResult result) {
		answer = test;
		return ("redirect:/manager");
	}
	/**
	 * login page user
	 * @return login.jsp - see src/main/webapp/WEB-INF/views/login.jsp
	 */
	@RequestMapping("/login")
	public String showAnswers() {
		return "login";
	}
	/**
	 * error page
	 * @return error.jsp - see src/main/webapp/WEB-INF/views/error.jsp
	 */
	@RequestMapping("/error")
	public String errot() {
		return "error";
	}
}
