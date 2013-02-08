package com.epam.logic.actions;

import java.util.HashMap;
import java.util.Map;

import com.epam.logic.Logger;

/**
 * @author Gordeenko_XP
 * Factory method for creating objects of actions.
 * Create classes for servlet ServletManager.
 */
public class AbstractActionsFactory {
	
	private final Logger logger = new Logger(org.apache.log4j.Logger.getLogger(AbstractActionsFactory.class.getClass()));
	protected Map<String, Class<?>> map = defaultMap(); // map for storing classes
	
	/**
	 * Get action class by key of map.
	 * @param actionName - key of map
	 * @return action class.
	 */
	public AbstractAction create(String actionName) {
		Class<?> clazz = (Class<?>) map.get(actionName);
		AbstractAction actionInstance = null;
		
		if (clazz == null) {
			throw new RuntimeException(getClass() + " was unable to find an action named '" + actionName + "'.");
		}
		
		try {
			actionInstance = (AbstractAction) clazz.newInstance();
		} catch (Exception exception) {
			logger.getExceptionTextFileLogger().error(exception);
		}

		return actionInstance;
	}
	
	/**
	 * Create and fill map of actions classes.
	 * Map has all classes with actions.
	 * @return map of actions classes.
	 */
	protected Map<String, Class<?>> defaultMap() {
		Map<String, Class<?>> map = new HashMap<String, Class<?>>();

		// Filling map.
		map.put("logonAction", LogonAction.class);
		map.put("regisrationAction", RegistrationAction.class);
		map.put("logoutAction", LogOffAction.class);
		map.put("showUsersAction", ShowUsersAction.class);
		map.put("showJobsAction", ShowJobsAction.class);
		map.put("addJobAction", AddJobAction.class);
		map.put("editJobAction", EditJobAction.class);
		map.put("redirectAction", RedirectAction.class);
		
		return map;
	}
	
}
