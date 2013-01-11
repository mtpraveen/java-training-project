package org.hopto.nexoff.race.controller;

import java.util.List;

import org.hopto.nexoff.race.domain.Bid;
import org.hopto.nexoff.race.domain.Horse;
import org.hopto.nexoff.race.domain.Race;
import org.hopto.nexoff.race.service.BidService;
import org.hopto.nexoff.race.service.HorseService;
import org.hopto.nexoff.race.service.RaceService;
import org.hopto.nexoff.race.service.UserService;
import org.hopto.nexoff.race.validator.BidValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/bids")
public class BidController {

	@Autowired
	private BidService bidService;
	@Autowired
	private RaceService raceService;
	@Autowired
	private UserService userService;
	@Autowired
	private HorseService horseService;
	@Autowired
	private BidValidator bidValidator;
	
	@RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
	public String getNew(@PathVariable("id") Long id, Model uiModel) {
		Bid bid = new Bid();
		Race race = raceService.findByIdFetch(id);
		bid.setRace(race);
		List<Horse> horses = race.getHorses();
 		uiModel.addAttribute("bid", bid);
 		uiModel.addAttribute("horses", horses);
		return "bids/new";
	}

	@RequestMapping(value = "/new/{id}", method = RequestMethod.POST)
	public String saveNew(@PathVariable("id") Long id, Bid bid, BindingResult result, Model uiModel) {
		bidValidator.validate(bid, result);
		if(result.hasErrors()){
			Race race = raceService.findByIdFetch(id);
			bid.setRace(race);
			List<Horse> horses = race.getHorses();
	 		uiModel.addAttribute("bid", bid);
	 		uiModel.addAttribute("horses", horses);
	 		return "bids/new";
		} else {
			bid.setRace(raceService.findById(id));
			bid.setUser(userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
			bidService.save(bid);
			return "redirect:/races";			
		}

	}
	
	
}
