package org.hopto.nexoff.race.controller;

import java.util.List;

import org.hopto.nexoff.race.domain.Horse;
import org.hopto.nexoff.race.service.HorseService;
import org.hopto.nexoff.race.validator.HorseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/horses")

public class HorseController {

	@Autowired
	private HorseService horseService;
	@Autowired
	private HorseValidator horseValidator;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel) {
		List<Horse> horses = horseService.findAll();
		Horse horse = new Horse();
		uiModel.addAttribute("horses", horses);
		uiModel.addAttribute("horse", horse);
		return "/horses/index";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveNew(Horse horse, BindingResult result, Model uiModel) {
		horseValidator.validate(horse, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("horse", horse);
			return "/horses/index";
		} else {
			horseService.save(horse);
			return "redirect:/horses/";
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable("id") Long id, Model uiModel) {
		Horse horse = horseService.findById(id);
		uiModel.addAttribute("horse", horse);
		return "horses/edit";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String saveEdit(Horse horse, BindingResult result, Model uiModel) {
		horseValidator.validate(horse, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("horse", horse);
			return "horses/edit";
		} else {
			horseService.save(horse);
			return "redirect:/horses/";
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		Horse horse = horseService.findById(id);
		horseService.delete(horse);
		return "redirect:/horses/";
	}

}
