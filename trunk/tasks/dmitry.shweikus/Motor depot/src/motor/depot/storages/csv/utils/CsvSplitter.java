package motor.depot.storages.csv.utils;

import java.util.ArrayList;

public class CsvSplitter
{
	public static ArrayList<String> parse(String s)
	{
		// main data holder
		StringBuilder builder = new StringBuilder();
		// array for results
		ArrayList<String> res = new ArrayList<String>();
		// flag indicates that we are in qualified sequence
		boolean inQuotes = false;
		boolean possibleInDoubleQuotes = false;
		for (char c : s.toCharArray())
		{
			if (possibleInDoubleQuotes)
				if (c != '"')
				{
					inQuotes = false;
					possibleInDoubleQuotes = false;
				}
			switch (c) {
			case ';':
				if (inQuotes)
					builder.append(c);
				else
				{
					res.add(builder.toString());
					builder = new StringBuilder();
				}
				break;
			case '"':
				if (!inQuotes)
					inQuotes = true;
				else if (possibleInDoubleQuotes)
				{
					builder.append('"');
					possibleInDoubleQuotes = false;
				}
				else
					possibleInDoubleQuotes = true;
				break;
			default:
				builder.append(c);
			}
		}
		res.add(builder.toString());
		return res;
	}
}
