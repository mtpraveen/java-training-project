package com.epam.logic;

import java.util.ResourceBundle;

public class Resource {
	
	private ResourceBundle resourceBundle;
	
	public Resource(String path) {
		// TODO check if path is correct and file exists.
		this.resourceBundle = ResourceBundle.getBundle(path);
	}
	
	public String getValue(String key) {
		return this.resourceBundle.getString(key);
	}

}
