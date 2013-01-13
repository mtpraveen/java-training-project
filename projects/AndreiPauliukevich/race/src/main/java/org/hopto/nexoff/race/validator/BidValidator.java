package org.hopto.nexoff.race.validator;

import org.hopto.nexoff.race.domain.Bid;
import org.hopto.nexoff.race.domain.User;
import org.hopto.nexoff.race.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BidValidator implements Validator{
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Bid.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "amount", "bid.amount.empty");
		User currentUser = userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Bid bid = (Bid) target;
		if (bid.getAmount() != null && bid.getAmount() > currentUser.getMoney()) {
			e.rejectValue("amount", "bid.amount.less");
		}
		if(bid.getRace().getWinner() != null ) {
			e.rejectValue("race", "bid.race.incorrect");
		}
		
	}
}
