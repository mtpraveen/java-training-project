package epam.com.votemanager.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import epam.com.votemanager.domain.Contact;
import epam.com.votemanager.service.IContactService;
import epam.com.votemanager.validator.ContactValidator;

@Controller
public class ContactController {

	@Autowired
	private IContactService contactService;

	@Autowired
	private ContactValidator contactValidator;

	@RequestMapping(value = "/administration/contacts", method = RequestMethod.GET)
	public String listContacts(Map<String, Object> map) {

		map.put("contact", new Contact());
		map.put("contactList", contactService.listContact());
		return "contact";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/results";
	}

	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public String administrarion() {
		return "redirect:/administration/contacts";
	}

	@RequestMapping(value = "/administration/contacts", method = RequestMethod.POST)
	public ModelAndView addContact(@ModelAttribute("contact") Contact contact,
			BindingResult result, ModelAndView ui) {
		contactValidator.validate(contact, result);
		if (result.hasErrors()) {
			ui.setViewName("contact");
			ui.addObject("contactList", contactService.listContact());
			return ui;
		} else {
			ui.setView(new RedirectView("/votemanager/administration/contacts"));
			contactService.addContact(contact);
			return ui;
		}
	}

	@RequestMapping(value = "/administration/deleteContact/{contactId}", method = RequestMethod.GET)
	public String deleteContact(@PathVariable("contactId") Integer contactId) {

		contactService.removeContact(contactId);

		return "redirect:/administration/contacts";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Map<String, Object> map) {
		map.put("contact", new Contact());
		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registration(
			@Valid @ModelAttribute("contact") Contact contact,
			BindingResult result, ModelAndView ui) {

		contactValidator.validate(contact, result);
		if (result.hasErrors()) {
			ui.setViewName("registration");
			return ui;
		} else {
			ui.setView(new RedirectView("/votemanager/"));
			contactService.addContact(contact);
			return ui;
		}
	}
}
