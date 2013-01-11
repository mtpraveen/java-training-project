package org.hopto.nexoff.race.validator;

import org.hopto.nexoff.race.domain.Race;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RaceValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Race.class.equals(clazz);
	}
	
	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "startTime", "race.startTime.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "coeff", "race.coeff.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "horses", "race.horses.empty");
		Race race = (Race) obj; 
		if ( race.getHorses() != null && race.getHorses().size() < 2){
			e.rejectValue("horses", "race.horses.tooSmall");
		}
	}

}
