package org.hopto.nexoff.race.controller;

import java.util.List;
import java.util.Locale;

import org.hopto.nexoff.race.domain.User;
import org.hopto.nexoff.race.service.UserService;
import org.hopto.nexoff.race.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
    private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel) {
		List<User> users = userService.findAll();
		uiModel.addAttribute("users", users);
		return "users/index";
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		User user = userService.findById(id);
		uiModel.addAttribute("user", user);
		return "users/show";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model uiModel) {
		User user = new User();
		uiModel.addAttribute("user", user);
		return "users/new";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveNew(User user, BindingResult result, Model uiModel, RedirectAttributes redirectAttrs, Locale locale) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("user", user);
			return "users/new";
		} else {
			userService.save(user);
			redirectAttrs.addFlashAttribute("message", messageSource.getMessage("user.create.sucsess", null, locale));
			return "redirect:/races";
		}

	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable("id") Long id, Model uiModel) {
		User user = userService.findById(id);
		uiModel.addAttribute("user", user);
		return "users/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String saveEdit(User user, BindingResult result, Model uiModel) {
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("user", user);
			return "users/edit";
		} else {
			userService.save(user);
			return "redirect:/users/show/" + user.getId().toString();
		}

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		userService.delete(user);
		return "redirect:/users/";
	}

}
