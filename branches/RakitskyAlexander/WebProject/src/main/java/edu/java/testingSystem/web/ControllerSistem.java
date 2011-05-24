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
 * @author Rakitsky Alexander
 * 
 */
@Controller
public class ControllerSistem {
	private Answer testing;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AnswersService testingService;

	@RequestMapping("/admin")
	public String listQuestion(Map<String, Object> map) {
		map.put("question", new Question());
		map.put("questionList", questionService.listQuestion());
		return "admin";
	}

	@RequestMapping("/user")
	public String listTests(Map<String, Object> map) {
		map.put("answer", new Answer());
		map.put("listAnswer", testingService.listAnswers());
		return "user";
	}
	@RequestMapping("/home")
	public String homePage(Map<String, Object> map)
	{
		User user = new User();
		user.setName(User.getUserNowName());
		map.put("userNow", user);
		return "home";
	}
	@RequestMapping("/")
	public String home() {
		return "redirect:/home";
	}

	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question,
			BindingResult result) {
		questionService.addQuestion(question);
		return "redirect:/admin";
	}

	@RequestMapping("/deleteQuestion/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId) {

		questionService.removeQuestion(contactId);

		return "redirect:/admin";
	}


	@RequestMapping("/addAnswers")
	public String addUserView(Map<String, Object> map) {
		User user = new User();
		user.setName(User.getUserNowName());
		map.put("userNow", user);
		map.put("questions", questionService.listQuestion());
		map.put("testing", new Answer());
		return "addAnswers";
	}
	
	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	public String addTest(@ModelAttribute("answer") Answer answer,
			BindingResult result) {
		answer.setUser(User.getUserNowName());
		testingService.addAnswer(answer);
		return "redirect:/addAnswers";
	}

	@RequestMapping("/showAnswers")
	public String showAnswers(Map<String, Object> map) {
		map.put("testList", testingService.listAnswerUserNow());
		map.put("testing", new Answer());
		return "showAnswers";
	}
	
	@RequestMapping("/deleteAnswer/{testId}")
	public String deleteTest(@PathVariable("testId") Integer testId) {
		testingService.removeAnswer(testId);
		return "redirect:/showAnswers";
	}

	@RequestMapping("/manager")
	public String manager(Map<String, Object> map) {
		Answer test = new Answer();
		map.put("testing", test);
		if (testing != null) {
			String language = testing.getLanguage();
			String level = testing.getLevel();
			if (language != null && level != null) {
				map.put("answersLang",testingService.listFindLanguage(language, level));
			}
			testing=null;
		}
		// map.put("listLang",
		// testingService.listFindLanguage(test.getLanguage(),
		// test.getLevel()));
		// else
		// map.put("listLang",null);
		return "manager";
	}

	@RequestMapping(value = "/setAnswers", method = RequestMethod.POST)
	public String setAnswers(@ModelAttribute("testing") Answer test,
			BindingResult result) {
		testing = test;
		return ("redirect:/manager");
	}
	 @RequestMapping("/login")
	 public String showAnswers()
	 {
	 return "login";
	 }
}
