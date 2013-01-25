package epam.course.webproject.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import epam.course.webproject.domain.Order;
import epam.course.webproject.domain.PasswordChanging;
import epam.course.webproject.domain.Role;
import epam.course.webproject.domain.User;
import epam.course.webproject.form.Message;
import epam.course.webproject.security.provider.UserDetailsServiceImpl;
import epam.course.webproject.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {

	final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MessageSource messageSource;

	@RequestMapping("/create")
	public String createUserForm(Model uiModel) {
		User user = new User();
		uiModel.addAttribute("user", user);
		return "user/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid User user, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		if (bindingResult.hasErrors()) {
			return "user/create";
		}
		//checks if a user with the same name in the database
		User userFromBd = userService.findById(user.getLogin());
		if (userFromBd != null) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"user_already_exist", null, locale)));
			return "user/create";
		}
		String encodedUserPassword = passwordEncoder.encodePassword(
				user.getPassword(), null);
		user.setPassword(encodedUserPassword);
		Set<Role> roles = new HashSet<Role>();
		Role role = new Role("user");
		roles.add(role);
		user.setRoles(roles);
		userService.save(user);
		redirectAttributes.addFlashAttribute("message", new Message("success",
				messageSource.getMessage("user_created", null, locale)));
		logger.info("User created");
		return "redirect:/mainpage";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/update")
	public String updateUserForm(Model uiModel) {
		//Get the current user
		Authentication at = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails ud = (UserDetails) at.getPrincipal();
		User user = userService.findById(ud.getUsername());
		uiModel.addAttribute("user", user);
		return "user/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		if (bindingResult.hasErrors()) {
			return "user/update";
		}
		User currentUser = userService.findById(user.getLogin());
		user.setRoles(currentUser.getRoles());
		userService.save(user);
		redirectAttributes.addFlashAttribute("message", new Message("success",
				messageSource.getMessage("user_updated", null, locale)));
		logger.info("User updated");
		return "redirect:/mainpage";
	}

	@RequestMapping(value = "/create/checklogin", method = RequestMethod.POST)
	public @ResponseBody
	String checkLogin(@RequestBody String login, Model uiModel, Locale locale) {
		String resultString = null;
		if (userService.findById(login) != null)
			resultString = "1";
		else
			resultString = "2";
		logger.info("Check login");
		return resultString;
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/changepassword")
	public String changePasswordForm(Model uiModel) {
		PasswordChanging passwordChanging = new PasswordChanging();
		uiModel.addAttribute("passwordChanging", passwordChanging);
		return "user/changePassword";
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public String changePassword(@Valid PasswordChanging passwordChanging,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		if (bindingResult.hasErrors()) {
			return "user/changePassword";
		}
		Authentication at = SecurityContextHolder.getContext()
				.getAuthentication();
		UserDetails ud = (UserDetails) at.getPrincipal();
		User user = userService.findById(ud.getUsername());
		String encodedUserOldPassword = passwordEncoder.encodePassword(
				passwordChanging.getOldPassword(), null);
		if (!user.getPassword().equals(encodedUserOldPassword)) {
			uiModel.addAttribute(
					"message",
					new Message("error", messageSource.getMessage(
							"password_not_match", null, locale)));
			return "user/changePassword";
		}
		String encodedUserNewPassword = passwordEncoder.encodePassword(
				passwordChanging.getNewPassword(), null);
		user.setPassword(encodedUserNewPassword);
		userService.save(user);
		uiModel.addAttribute(user);
		uiModel.addAttribute(
				"message",
				new Message("success", messageSource.getMessage(
						"password_is_changed", null, locale)));
		logger.info("User updated");
		return "user/update";
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping("/list")
	public String showUsers(Model uiModel) {
		List<User> users = userService.findAll();
		List<Integer> numberOfOrders = new ArrayList<Integer>();
		List<Integer> totalSum = new ArrayList<Integer>();
		List<Boolean> listIsAdmin=new ArrayList<Boolean>();
		int i=0;
		for (User u : users) {
			numberOfOrders.add(u.getOrders().size());
			int totalSumForOneUser = 0;
			for (Order o : u.getOrders()) {
				totalSumForOneUser += o.getCost();
			}
			totalSum.add(totalSumForOneUser);
			listIsAdmin.add(false);
			for (Role r: u.getRoles()) {
				if (r.getName().equalsIgnoreCase("admin"))
					listIsAdmin.set(i, true);
			}
			i++;
		}
		uiModel.addAttribute("listIsAdmin", listIsAdmin);
		uiModel.addAttribute("users", users);
		uiModel.addAttribute("numberOfOrders", numberOfOrders);
		uiModel.addAttribute("totalSum", totalSum);
		return "user/list";
	}

	@RequestMapping(value = "/delete/{login}", method = RequestMethod.GET)
	public @ResponseBody
	void deleteUser(@PathVariable("login") String login) {
		if (userService.findById(login) != null)
			userService.remove(login);
	}

}
