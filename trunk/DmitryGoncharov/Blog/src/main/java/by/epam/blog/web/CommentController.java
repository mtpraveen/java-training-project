package by.epam.blog.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import by.epam.blog.model.Comment;
import by.epam.blog.service.BlogServiceImpl;
import by.epam.blog.service.CommentServiceImpl;
import by.epam.blog.service.TopicServiceImpl;

/**
 * @author Dmitry_Goncharov
 *
 */
@Controller
public class CommentController {
	@Autowired
	private CommentServiceImpl commentService;
	
	@Autowired
	private TopicServiceImpl topicService;

	@RequestMapping(value = "/addcomment/{topicId}")
	public ModelAndView showForm(@PathVariable("topicId") long topicId, Model model) {
		model.addAttribute("topicid", topicId);
		return new ModelAndView("addcomment", "command", new Comment());
	}
	
	@RequestMapping(value = "/createcomment/{topicId}")
	public String createComment(@PathVariable("topicId") long topicId,@ModelAttribute Comment comment, BindingResult result, Model model) {
		comment.setTopic(topicService.findTopicById(topicId));
		comment.setDate(new Date());
		commentService.saveComment(comment);
		//return "redirect:/blog/"+topicId;
		return "redirect:/spisok";
	}
}
