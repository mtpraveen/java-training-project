package edu.java.testingSystem.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.java.question.service.QuestionService;
import edu.java.testingSystems.domain.Question;
import edu.java.testingSystems.domain.User;

@Controller
public class QuestionController {
	/**
	 * Autocomplete field settings are in folder src / main / webapp / WEB-INF /
	 * spring / data.xml
	 */
	@Autowired
	private QuestionService questionService;

	/**
	 * Adding and deleting questions. This page is available only to the
	 * administrator.
	 * 
	 * @param map
	 * @return admin.jsp - see src/main/webapp/WEB-INF/views/admin.jsp
	 */
	@RequestMapping("/admin")
	public String listQuestion(Map<String, Object> map) {
		User user = new User();
		user.setName(User.getUserNowName());
		map.put("userNow", user);
		map.put("question", new Question());
		map.put("questionList", questionService.listQuestion());
		return "admin";
	}

	/**
	 * Adding a question is only available to the administrator to redirect
	 * admin.jsp
	 * 
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
	 * 
	 * @see edu.java.testingSystem.web.ControllerSistem#listQuestion(Map)
	 */
	@RequestMapping("/deleteQuestion/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId) {
		questionService.removeQuestion(contactId);
		return "redirect:/admin";
	}
}
