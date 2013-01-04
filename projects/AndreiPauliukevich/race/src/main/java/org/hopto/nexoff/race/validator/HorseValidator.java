package org.hopto.nexoff.race.validator;

import org.hopto.nexoff.race.domain.Horse;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class HorseValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Horse.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "horce.name.empty");
		
	}

}
