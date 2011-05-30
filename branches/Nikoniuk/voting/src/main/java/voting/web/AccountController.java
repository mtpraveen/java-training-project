package voting.web;

import static voting.security.VotingUserDetailsService.getCurrentAccount;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import nl.captcha.Captcha;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import voting.domain.Account;
import voting.domain.Category;
import voting.domain.Gender;
import voting.domain.SystemRole;

@Controller
public class AccountController {

	private static final  Logger LOGGER = Logger.getLogger(AccountController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String create(@Valid Account account, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		LOGGER.info(httpServletRequest.getRemoteAddr()
				+ " tries to register new account...");
		if (!account.getPassword().equals(account.getConfirmPassword())) {
			bindingResult.rejectValue("confirmPassword",
					"error_passwords_mistmatch");
		}
		if (Account.findAccountByName(account.getName()) != null) {
			bindingResult.rejectValue("name", "error_account_exists");
		}

		if (Account.findAccountByEmail(account.getEmail()) != null) {
			bindingResult.rejectValue("email", "error_email_exists");
		}

		Captcha captcha = (Captcha) httpServletRequest.getSession()
				.getAttribute(Captcha.NAME);
		if (!captcha.isCorrect(account.getCaptchaText())) {
			bindingResult.rejectValue("captchaText", "error_wrong_captcha");
		}

		// first registered user should be administrator
		if (Account.countAccounts() == 0)
			account.setSystemRole(SystemRole.ADMIN);
		else
			account.setSystemRole(SystemRole.USER);

		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("account", account);
			LOGGER.warn(httpServletRequest.getRemoteAddr()
					+ ": wrong registration data");
			return "accounts/register";
		}
		uiModel.asMap().clear();
		account.persist();
		LOGGER.info("new account " + account.getName()
				+ " was successfully registered");
		return "redirect:/userinfo/" + account.getId();
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String createForm(Model uiModel,
			HttpServletRequest httpServletRequest) {
		httpServletRequest.getSession().setAttribute(Captcha.NAME, null);
		uiModel.addAttribute("account", new Account());
		return "accounts/register";
	}

	@RequestMapping(value = "/userinfo/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("account", Account.findAccount(id));
		uiModel.addAttribute("itemId", id);
		return "accounts/show";
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute("accounts", Account.findAccountEntries(
					page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) Account.countAccounts() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("accounts", Account.findAllAccounts());
		}
		return "accounts/list";
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.PUT)
	public String update(@Valid Account account, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		Account oldAccount = Account.findAccount(account.getId());
		LOGGER.info(getCurrentAccount(httpServletRequest)
				+ " tries to update account " + oldAccount.getName() + "...");
		// if name was changed, but such name already exists in database
		if (!account.getName().equalsIgnoreCase(oldAccount.getName())
				&& Account.findAccountByName(account.getName()) != null) {
			bindingResult.rejectValue("name", "error_account_exists");
		}

		// if email was changed, but such email already exists in database
		if (!account.getEmail().equalsIgnoreCase(oldAccount.getEmail())
				&& Account.findAccountByEmail(account.getEmail()) != null) {
			bindingResult.rejectValue("email", "error_email_exists");
		}
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("account", account);
			LOGGER.warn(getCurrentAccount(httpServletRequest)
					+ ": wrong update data for account " + oldAccount.getName());
			return "accounts/update";
		}
		uiModel.asMap().clear();
		account.merge();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": account "
				+ oldAccount.getName() + " was successfully updated");
		return "redirect:/userinfo/" + account.getId();
	}

	@RequestMapping(value = "/accounts/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("account", Account.findAccount(id));
		return "accounts/update";
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest httpServletRequest) {
		Account account = Account.findAccount(id);
		LOGGER.info(getCurrentAccount(httpServletRequest)
				+ ": tries to remove account " + account.getName() + "...");
		account.remove();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": account "
				+ account.getName() + " was successfully removed ");
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/accounts";
	}

	@ModelAttribute("accounts")
	public Collection<Account> populateAccounts() {
		return Account.findAllAccounts();
	}

	@ModelAttribute("systemroles")
	public java.util.Collection<SystemRole> populateSystemRoles() {
		return Arrays.asList(SystemRole.class.getEnumConstants());
	}

	@ModelAttribute("genders")
	public java.util.Collection<Gender> populateGenders(
			HttpServletRequest httpServletRequest) {
		return Arrays.asList(Gender.class.getEnumConstants());
	}

	@ModelAttribute("date_format")
	String addDateTimeFormatPatterns() {
		return DateTimeFormat.patternForStyle("S-",
				LocaleContextHolder.getLocale());
	}

	@ModelAttribute("categories_menu")
	public java.util.Map<Category, Long> populateCategories() {
		LinkedHashMap<Category, Long> categories = new LinkedHashMap<Category, Long>();
		for (Category category : Category.findAllCategories()) {
			categories.put(category, Category.countQuestions(category));
		}
		return categories;
	}

}
