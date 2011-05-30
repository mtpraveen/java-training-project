package by.brsu.java.training.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static String dateFormat(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("dd  MMM  yyyy,  H : m", Locale.US);
		Locale.setDefault(Locale.US);
		df.applyPattern("dd  MMM  yyyy,  HH : mm");
		return df.format(date);
	}

}
