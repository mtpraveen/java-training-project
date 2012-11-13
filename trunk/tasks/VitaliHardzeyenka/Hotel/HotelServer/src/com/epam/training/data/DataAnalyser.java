package com.epam.training.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Analyze data received from text file with different ways. 
 * @author Gordeenko
 */
public class DataAnalyser {
	
	/**
	 * Slit line with separator.
	 * @param line string which need to split
	 * @param separator
	 * @return splitted line by separator.
	 */
	public static ArrayList<String> split(String line, String separator) {
		return new ArrayList<String>(Arrays.asList(line.split(separator)));
	}
}
