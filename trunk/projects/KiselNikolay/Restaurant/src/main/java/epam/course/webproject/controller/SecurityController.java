package epam.course.webproject.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import epam.course.webproject.form.Message;

@RequestMapping("/security")
@Controller
public class SecurityController {

	final Logger logger = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	MessageSource messageSource;
	
	@RequestMapping("/loginfail")
	public String loginFail(Model uiModel, Locale locale) {
		uiModel.addAttribute("message", new Message("error",
			messageSource.getMessage("irregular_login_or_password", null, locale)));
		return "main/mainpage";
		
	}
}
