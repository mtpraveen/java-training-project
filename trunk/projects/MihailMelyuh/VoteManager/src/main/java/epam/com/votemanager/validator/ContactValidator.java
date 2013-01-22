package epam.com.votemanager.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import epam.com.votemanager.domain.Contact;
import epam.com.votemanager.service.IContactService;

@Component
public class ContactValidator implements Validator {

	@Autowired
	private IContactService contactService;

	private Pattern mail = Pattern.compile(".+@.+");
	private Pattern telephone = Pattern.compile("[0-9]{6}");

	@Override
	public boolean supports(Class<?> clazz) {
		return Contact.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Contact contact = (Contact) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",
				"label.notEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
				"label.notEmpty");
		if (!contactService.isUniqueEmail(contact)) {
			errors.rejectValue("email", "label.alreadyExists");
		}
		Matcher matcher = mail.matcher(contact.getEmail());
		if (!matcher.matches()) {
			errors.rejectValue("email", "label.incorrectFormat");
		}
		if (!(contact.getTelephone().isEmpty() || contact.getTelephone() == null)) {
			matcher = telephone.matcher(contact.getTelephone());
			if (!matcher.matches()) {
				errors.rejectValue("telephone", "label.incorrectFormat");
			}
		}
	}

}
