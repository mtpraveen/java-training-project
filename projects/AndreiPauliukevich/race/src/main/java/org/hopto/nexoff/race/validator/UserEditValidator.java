package org.hopto.nexoff.race.validator;

import org.hopto.nexoff.race.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserEditValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "fio", "user.name.empty");
		ValidationUtils.rejectIfEmpty(e, "password", "user.pwd.empty");
		ValidationUtils.rejectIfEmpty(e, "email", "user.email.empty");
		User user = (User) obj;
		if (user.getPassword().length() > 20 || user.getPassword().length() < 5){
			e.rejectValue("password", "user.pwd.wrong");
		} 
	}
	
	

}
