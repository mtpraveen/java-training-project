package sample;

import java.util.StringTokenizer;

public class StringTestNo1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "Test text is made for test. Text not for test is not meant for test. Murm merm marm.";
		StringBuffer resultString = new StringBuffer();
		StringTokenizer st = new StringTokenizer(text, " .,:-\n\t\r");
		
		try {
		while (st.hasMoreTokens()) {
			String temp = new String(st.nextToken().toLowerCase());
			if (temp.charAt(0) == temp.charAt(temp.length()-1) && !resultString.toString().contains(temp)) {
				resultString.append(" ").append(temp);
			}
		}
		} catch (Exception e) {
			System.out.println("An error occured -> " + e);
		}
		
		System.out.println("Original text --> " + text);
		System.out.println("Result --> " + resultString.toString().trim());
	}
}
