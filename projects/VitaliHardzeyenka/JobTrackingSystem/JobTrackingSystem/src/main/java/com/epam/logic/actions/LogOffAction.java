package com.epam.logic.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author EXHUMLOKI
 * Describe user ending work with system.
 * Direct to farewell page and destroy the current session. 
 */
public class LogOffAction extends AbstractAction {

	@Override
	public String perform(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();		
		session.removeAttribute("user");
		session.removeAttribute("locale");		
		session.invalidate();
		return "/logOff.jsp";
	}
	
}
