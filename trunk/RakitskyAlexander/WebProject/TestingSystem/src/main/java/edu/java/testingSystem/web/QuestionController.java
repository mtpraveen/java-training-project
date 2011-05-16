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

import edu.java.testing.service.TestingService;
import edu.java.testingSystem.domain.Question;
import edu.java.testingSystem.domain.Testing;
import edu.java.testingSystem.service.QuestionService;

/**
 * @author Rakitsky Alexander
 * 
 */
@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private TestingService testingService;
	
	@RequestMapping("/index")
	public String listQuestion(Map<String, Object> map) {
		map.put("question", new Question());
		map.put("questionList", questionService.listQustion());
		return "question";
	}

	@RequestMapping("/user")
	public String listTests(Map<String, Object> map) {
		map.put("testing", new Testing());
		map.put("testingList", testingService.listTesting());
		return "user";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/user";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addQuestion(@ModelAttribute("question") Question question,
			BindingResult result) {
		questionService.addQuestion(question);
		return "redirect:/index";
	}

	@RequestMapping("/delete/{contactId}")
	public String deleteContact(@PathVariable("contactId") Integer contactId) {

		questionService.removeQuestion(contactId);

		return "redirect:/index";
	}
	@RequestMapping(value="/addtest",method=RequestMethod.POST)
	public String addTest(@ModelAttribute("test") Testing testing, BindingResult result)
	{
		testingService.addTesting(testing);
		return "redirect:/user";
	}
	@RequestMapping("/deleteTest/{testId}")
	public String deleteTest(@PathVariable("testId") Integer testId){
		testingService.removeTesting(testId);
		return "redirect:/user";
	}
}
