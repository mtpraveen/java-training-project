package epam.com.votemanager.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import epam.com.votemanager.domain.Question;
import epam.com.votemanager.service.IContactService;
import epam.com.votemanager.service.IQuestionService;

@Controller
public class VoteController {

	@Autowired
	private IQuestionService questionService;

	@Autowired
	private IContactService contactService;

	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public String listQuestions(Map<String, Object> map) {
		map.put("question", new Question());
		map.put("questionList", contactService.questionForCurrentUser());
		return "vote";
	}

	@RequestMapping(value = "/poll", method = RequestMethod.POST)
	public String vote(@RequestParam("vote") Integer voteId) {

		contactService.vote(voteId);

		return "redirect:/vote";
	}

}
