/**
 * 
 */
package org.bsu.str1;

import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

/**
 * @author Admin
 * @version 1.0
 */
public class str {
	static String s = "asdf, asd5, drd, dr45d, drd sdr45ew,oro, frf";
	static String s2 = "asdf, 400, 300, 4,4, 5 5 drd sdrew,oro, frf";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** Level_1 */
		System.out.print("Task_1:   ");
		StringTokenizer st = new StringTokenizer(s, "  \t\n\r,.");
		HashSet result = new HashSet();
		while (st.hasMoreTokens()) {
			String buf = st.nextToken();
			int len = buf.length();
			if (buf.charAt(0) == buf.charAt((len) - 1))
				result.add(buf);
		}
		System.out.println(result);
		// TODO Auto-generated method stub

		
		
		/** Level_2 */
		System.out.print("Task_2:   ");
		StringTokenizer tokenizer = new StringTokenizer(s2, " ,;:!@#$%^&*()+=_");
		// Суммируем
		int sum = 0;
		while (tokenizer.hasMoreTokens()) {
			try {
				sum += Integer.parseInt(tokenizer.nextToken());
			} catch (NumberFormatException ex) {
				// System.out.println("Wrong number format");
			}
		}
		System.out.println("Sum=" + sum);
		
			
		/** Level_3 */
		System.out.print("Task_3:   ");
		StringTokenizer stp = new StringTokenizer(s, "  \t\n\r,.", true);
			while (stp.hasMoreTokens()) {
			String buf = stp.nextToken();
			String a = buf.substring(0, 1).toUpperCase();
			String b = buf.substring(1).toLowerCase();
			String p = a + b;
			System.out.print(" " + p);
	
			 
		}
			
	}
}
