package org.hopto.nexoff.race.validator;

import org.hopto.nexoff.race.domain.Bid;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BidValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Bid.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "bid.amount.empty");

	}

}
