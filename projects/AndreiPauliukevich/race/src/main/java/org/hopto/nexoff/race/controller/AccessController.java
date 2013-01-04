package org.hopto.nexoff.race.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccessController {
	
	
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
	public String loginFailure() {
		return "access/login";
	}

}
