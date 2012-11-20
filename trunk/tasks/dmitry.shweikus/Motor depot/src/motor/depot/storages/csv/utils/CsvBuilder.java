/**
 * 
 */
package motor.depot.storages.csv.utils;

import java.util.ArrayList;

/**
 * @author dima
 *
 */
public class CsvBuilder
{
	public String create(ArrayList<String> list)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i<list.size();i++)
		{
			if (i>0)
				builder.append(";");
			String data = list.get(i);
			boolean hasDelim = data.indexOf(";") != -1;
			boolean hasQuotes = data.indexOf("\"") != -1;
			if (hasQuotes)
				data = data.replace("\"", "\"\"");
			if (hasDelim || hasQuotes)
			{
				builder.append('"');
				builder.append(data);
				builder.append('"');
			}
			else
				builder.append(data);
		}
		return builder.toString();
	}
}
