package org.hopto.nexoff.race.controller;

import java.util.List;

import org.hopto.nexoff.race.domain.Bid;
import org.hopto.nexoff.race.domain.Race;
import org.hopto.nexoff.race.domain.User;
import org.hopto.nexoff.race.service.BidService;
import org.hopto.nexoff.race.service.HorseService;
import org.hopto.nexoff.race.service.RaceService;
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
@RequestMapping("/bid")
public class BidController {

	@Autowired
	private BidService bidService;
	@Autowired
	private RaceService raceService;
	@Autowired
	private HorseService horseService;
	@Autowired
	private BidValidator bidValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel) {
		List<Bid> bids = bidService.findAll(getCurrentUser());
		uiModel.addAttribute("bids", bids);
		return "bid/index";
	}

	@RequestMapping(value = "/new/{id}", method = RequestMethod.GET)
	public String getNew(@PathVariable("id") Long id, Model uiModel) {
		Bid bid = new Bid();
		Race race = raceService.findByIdFetch(id);
		uiModel.addAttribute("race", race);
		uiModel.addAttribute("bid", bid);
 		uiModel.addAttribute("horses", race.getHorses());
		return "bid/new";
	}

	@RequestMapping(value = "/new/{race_id}", method = RequestMethod.POST)
	public String saveNew(@PathVariable("race_id") Long race_id, Bid bid, BindingResult result, Model uiModel) {
		bid.setUser(getCurrentUser());
		bid.setRace(raceService.findByIdFetch(race_id));
		bidValidator.validate(bid, result);
		if(result.hasErrors()){
			uiModel.addAttribute("race", raceService.findByIdFetch(race_id));
	 		uiModel.addAttribute("bid", bid);
	 		uiModel.addAttribute("horses", raceService.findByIdFetch(race_id).getHorses());
			return "bid/new";
		} else {
			bidService.save(bid);
			return "redirect:/race";
		}

	}
	
	public User getCurrentUser() {
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
