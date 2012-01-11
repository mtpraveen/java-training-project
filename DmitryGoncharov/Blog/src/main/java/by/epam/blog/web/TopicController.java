package by.epam.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import by.epam.blog.model.Blog;
import by.epam.blog.model.Comment;
import by.epam.blog.model.Topic;
import by.epam.blog.service.BlogServiceImpl;
import by.epam.blog.service.TopicServiceImpl;

/**
 * @author Dmitry_Goncharov
 * 
 */
@Controller
public class TopicController {

	@Autowired
	private TopicServiceImpl topicService;

	@Autowired
	private BlogServiceImpl blogService;
	
	@RequestMapping(value = "/addtopic/{blogId}")
	public ModelAndView showForm(@PathVariable("blogId") long blogId,
			Model model) {
		model.addAttribute("blogid", blogId);
		return new ModelAndView("addtopic", "command", new Topic());
	}

	@RequestMapping(value = "/createtopic/{blogId}")
	public String createComment(@PathVariable("blogId") long blogId,
			@RequestParam("login") String login,
			@RequestParam("pass") String pass,
			@RequestParam("topic_caption") String caption,
			@RequestParam("topic_text") String text,
			Model model) {
		System.out.println("begin");
		
		Topic topic = new Topic();
		topic.setCaption(caption);
		topic.setText(text);
		Blog blog = blogService.findBlogByLogin(login, pass);
		topic.setBlog(blog);
		
		//Бред какойто но без етого не работает...
		topic.setId(555L);
		
		blog.getTopics().add(topic);
		System.out.println("test"+topic.getBlog().getId());
		topicService.saveTopic(topic);
		
		System.out.println("end");
		return "redirect:/spisok";
	}

}
