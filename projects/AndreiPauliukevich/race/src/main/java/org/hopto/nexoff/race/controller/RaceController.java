package org.hopto.nexoff.race.controller;

import java.util.List;

import org.hopto.nexoff.race.domain.Race;
import org.hopto.nexoff.race.service.HorseService;
import org.hopto.nexoff.race.service.RaceService;
import org.hopto.nexoff.race.validator.RaceEditValidator;
import org.hopto.nexoff.race.validator.RaceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/races")
public class RaceController {

	@Autowired
	private RaceService raceService;
	@Autowired
	private HorseService horseService;
	@Autowired
	private RaceValidator raceValidator;
	@Autowired
	private RaceEditValidator raceEditValidator;

	/*		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());*/
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel) {
		List<Race> races = raceService.findAll();
		uiModel.addAttribute("races", races);
		return "/races/index";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model uiModel) {
		Race race = new Race();
		uiModel.addAttribute("horses", horseService.findAll());
		uiModel.addAttribute("race", race);
		
		return "races/new";
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String saveNew(Race race, BindingResult result, Model uiModel) {
		raceValidator.validate(race, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("race", race);
			uiModel.addAttribute("horses", horseService.findAll());
			return "races/new";
		} else {
			raceService.save(race);
			return "redirect:/races/";
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		raceService.delete(raceService.findById(id));
		return "redirect:/races/";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable("id") Long id, Model uiModel) {
		Race race = raceService.findByIdFetch(id);
		uiModel.addAttribute("race", race);
		return "races/edit";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public String saveEdit(Race race, BindingResult result, Model uiModel) {
		if (result.hasErrors()) {
			uiModel.addAttribute("race", race);
			return "races/edit";
		} else {
			raceService.save(race);
			return "redirect:/races/";
		}

	}
	

	

}
