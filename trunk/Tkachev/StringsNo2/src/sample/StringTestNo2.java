package sample;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class StringTestNo2 {
	private static String text = new String(
			"This is a text to test some string functions"
					+ ", 20-30 available ones, test numbers are: 1, 2, 3, 5.1, 23");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(text, "\t\n\r,-: ");

		while (st.hasMoreTokens()) {
			try {
				Integer i = Integer.parseInt(st.nextToken());
				numbers.add(i);
			} catch (NumberFormatException e) {
				//System.out.println("Cannot convert to Integer... -> " + e);
			}
		}
		
		int sum = 0;
		for (Integer temp: numbers) {
			sum = sum+temp.intValue();
		}
		
		System.out.println("Text -> " + text);
		System.out.println("Numbers array -> " + numbers);
		System.out.println("Sum of all int values in text -> " + sum);
	}

}
