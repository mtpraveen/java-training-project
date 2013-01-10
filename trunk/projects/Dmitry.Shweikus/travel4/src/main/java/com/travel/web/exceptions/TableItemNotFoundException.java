/**
 * 
 */
package com.travel.web.exceptions;

import javax.servlet.ServletException;

/**
 * @author dima
 *
 */
public class TableItemNotFoundException extends ServletException
{
	String tableName;
	long id;
	
	/**
	 * @param tableName
	 * @param id
	 */
	public TableItemNotFoundException(String tableName, long id) {
		super();
		this.tableName = tableName;
		this.id = id;
	}

	@Override
	public String getMessage()
	{
		return "Item with id " + id + " not found in table " + tableName;
	}
}
