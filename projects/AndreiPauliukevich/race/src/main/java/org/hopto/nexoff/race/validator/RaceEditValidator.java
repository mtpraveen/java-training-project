package org.hopto.nexoff.race.validator;

import org.hopto.nexoff.race.domain.Race;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RaceEditValidator implements Validator  {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Race.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors e) {


	}

}
