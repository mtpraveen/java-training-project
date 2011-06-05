package edu.java.auction.web;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.java.auction.domain.Tovar;
import edu.java.auction.domain.User;
import edu.java.auction.service.TovarService;




@Controller
public class TovarController {
	private Tovar tovar;
	
	@Autowired
	private TovarService tovarService;
	
	
	@RequestMapping("/index")
	public String listTovars(Map<String, Object> map) {

		map.put("tovar", new Tovar());
		map.put("tovarList", tovarService.listTovar());

		return "tovar";
	}
	
	/*@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}*/

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addTovar(@ModelAttribute("tovar") Tovar tovar,
			BindingResult result) {

		tovarService.addTovar(tovar);

		return "redirect:/auc";
	}

	@RequestMapping("/delete/{tovarId}")
	public String deleteTovar(@PathVariable("tovarId") Integer tovarId) {

		tovarService.removeTovar(tovarId);

		return "redirect:/auc";
	}
	
	
	/*@RequestMapping("/auction")
	public String Auction() {
		// setting userNow

		return "a";
	}*/
	

	@RequestMapping("/pay/{tovarId}")
	public String payTovar(@PathVariable("tovarId") Integer tovarId) {

		tovarService.payTovar(tovarId);

		return "redirect:/index";
	}
	
	
	
	@RequestMapping("/auc")
	public String listAuction(Map<String, Object> map) {

		map.put("tovar", new Tovar());
		map.put("tovarList", tovarService.listTovar());

		return "a";
	}
	
	
	@RequestMapping("/find")
	public String tovar(Map<String, Object> map) {
		Tovar tovars = new Tovar();
		map.put("tovar", tovars);
		if (tovar != null) {
			String name = tovar.getName();
			if (name != null) {
				map.put("tovarsName",
						tovarService.listFindTovar(name));	}
			tovar = null;
		}
		return "find";
	}
	
	
	@RequestMapping(value = "/setTovars", method = RequestMethod.POST)
	public String setTovars(@ModelAttribute("tovar") Tovar test,
			BindingResult result) {
		tovar = test;
		return ("redirect:/find");
	}
	
	
	@RequestMapping("/login")
	public String showAnswers() {
		return "login";
	}
	
	
	
	@RequestMapping("/home")
	public String homePage(Map<String, Object> map) {
		User user = new User();
		user.setName(User.getUserNowName());
		map.put("userNow", user);
		return "home";
			
	}


	@RequestMapping("/")
	public String home() {
		return "redirect:/home";
	}
	
	
	
	@RequestMapping("/error")
	public String errot() {
		return "error";
	}
	
	@RequestMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
}

