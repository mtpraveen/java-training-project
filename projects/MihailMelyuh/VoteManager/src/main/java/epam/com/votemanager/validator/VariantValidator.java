package epam.com.votemanager.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import epam.com.votemanager.domain.Variant;

@Component
public class VariantValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Variant.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "variant", "label.notEmpty");
	}

}
