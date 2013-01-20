/**
 * 
 */
package com.travel.web.beans;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author dima
 *
 */
public class CalendarWrapper
{
	private Calendar calendar = GregorianCalendar.getInstance();

	public Date getDate()
	{
		return new Date(calendar.getTimeInMillis());
	}

	public void setDate(Date date)
	{
		calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
	}
	
	public int getYear()
	{
		return calendar.get(Calendar.YEAR);
	}
	
	public int getMonth()
	{
		return calendar.get(Calendar.MONTH);
	}

	public int getDay()
	{
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

}
