package epam.course.webproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/")
@Controller
public class MainPageController {

	final Logger logger = LoggerFactory.getLogger(DishController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		return "redirect:/mainpage";
	}
	
	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String mainPage(Model uiModel) {
		logger.info("load mainpage");
		return "main/mainpage";
	}
}
