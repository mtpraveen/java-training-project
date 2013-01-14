/**
 * 
 */
package com.travel.web.servlets.dispatcher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.travel.exceptions.InvalidRequest;
import com.travel.pojo.User;
import com.travel.web.servlets.actions.AbstractAction;
import com.travel.web.servlets.actions.ActionNames;
import com.travel.web.servlets.actions.AutentificationRequiredAction;
import com.travel.web.servlets.actions.DefaultAction;
import com.travel.web.servlets.actions.DeleteAction;
import com.travel.web.servlets.actions.UpdateAction;
import com.travel.web.servlets.actions.UserAction;
import com.travel.web.servlets.actions.ViewAction;

/**
 * @author dima
 *
 */
public class RequestDispatcher
{
	public AbstractAction getAction(HttpServletRequest request, HttpServletResponse response,User user) throws InvalidRequest
	{
		System.out.println("REQUEST: " + request.getServletPath());
		List<String> pathParams = new ArrayList<>();
		for (String string : request.getServletPath().split("-"))
		{
			string = string.replace("/", "");
			if(!"".equals(string))
				pathParams.add(string);
		}
		//================
		AbstractAction action;
		if (pathParams.size() == 0)
			action = new DefaultAction();
		else
			switch (pathParams.get(0)) {
			case ActionNames.INDEX :  action = new DefaultAction(); break;
			case ActionNames.LOGIN :  action = new UserAction(); break;
			case ActionNames.LOGOUT: action = new UserAction(); break;
			case ActionNames.VIEW  :  action = new ViewAction(); break;	
			case ActionNames.EDIT  :  action = new ViewAction(); break;	
			case ActionNames.SHOW  :  action = new ViewAction(); break;	
			case ActionNames.NEW   :  action = new ViewAction(); break;	
			case ActionNames.SAVE :  action = new UpdateAction(); break;	
			case ActionNames.DELETE:  action = new DeleteAction(); break;	
			default:
				throw new InvalidRequest("Invalid request : \"" + request.getServletPath() + "\"");
			}
		//================
		action.setPathParams(pathParams);
		action.setUser(user);
		return action;
	}
	public AbstractAction getAutentificationRequiredAction(HttpServletRequest request, HttpServletResponse response,User user) throws InvalidRequest
	{
		AbstractAction action = new AutentificationRequiredAction();
		action.setPathParams(new ArrayList<String>());
		action.setUser(user);
		return action;
	}
}
