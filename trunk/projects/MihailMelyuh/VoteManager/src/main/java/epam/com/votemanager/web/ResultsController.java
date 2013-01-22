package epam.com.votemanager.web;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import epam.com.votemanager.domain.Question;
import epam.com.votemanager.service.IQuestionService;
import epam.com.votemanager.service.IVariantService;

@Controller
public class ResultsController {

	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IVariantService variantService;

	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String listQuestions(Map<String, Object> map) {

		map.put("today", false);
		map.put("question", new Question());
		map.put("questionList", questionService.listResults());

		return "results";
	}

	@RequestMapping(value = "/today", method = RequestMethod.GET)
	public String todayResults(Map<String, Object> map) {

		map.put("today", true);
		Date date = new Date(System.currentTimeMillis());
		map.put("question", new Question());
		map.put("questionList", questionService.todayResult(date));

		return "results";
	}
}
