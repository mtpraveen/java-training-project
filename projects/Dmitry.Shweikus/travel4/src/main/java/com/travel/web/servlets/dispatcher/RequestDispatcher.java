/**
 * 
 */
package com.travel.web.servlets.dispatcher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import com.travel.pojo.User;
import com.travel.web.exceptions.InvalidRequest;
import com.travel.web.servlets.actions.AbstractAction;
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
			case "index" :  action = new DefaultAction(); break;
			case "login" :  action = new UserAction(); break;
			case "logout": action = new UserAction(); break;
			case "view"  :  action = new ViewAction(); break;	
			case "edit"  :  action = new ViewAction(); break;	
			case "show"  :  action = new ViewAction(); break;	
			case "new"   :  action = new ViewAction(); break;	
			case "save" :  action = new UpdateAction(); break;	
			case "delete":  action = new DeleteAction(); break;	
			default:
				throw new InvalidRequest();
			}
		//================
		action.setPathParams(pathParams);
		action.setUser(user);
		return action;
	}
}
