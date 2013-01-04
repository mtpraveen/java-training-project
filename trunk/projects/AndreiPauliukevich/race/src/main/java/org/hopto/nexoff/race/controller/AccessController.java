package org.hopto.nexoff.race.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccessController {
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping("/login")
	public String login(Model model) {
		
		return "access/login";
	}
	
	@RequestMapping("/denied")
	public String denied(Model model, @RequestParam(required = false) String message) {
		model.addAttribute("message", message);
		return "access/denied";
	}
	
	@RequestMapping(value = "/failure", method = RequestMethod.GET)
	public String loginFailure(RedirectAttributes redirectAttrs, Locale locale) {
		redirectAttrs.addFlashAttribute("message", messageSource.getMessage("login.failure", null, locale));
		return "redirect:/login";

	}

}
