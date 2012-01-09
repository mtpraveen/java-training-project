package by.epam.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import by.epam.blog.model.Topic;
import by.epam.blog.service.TopicServiceImpl;

/**
 * @author Dmitry_Goncharov
 *
 */
@Controller
public class TopicController {
	
	@Autowired
	private TopicServiceImpl topicService;
	
	@RequestMapping(value = "/addtopic/{blogId}")
	public ModelAndView showForm(@PathVariable("blogId") long blogId, Model model) {
		//model.addAttribute("blogid", blogId);
		return new ModelAndView("addtopic", "command", new Topic());
	}
}
