/**
 * 
 */
package motor.depot.clientserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author dima
 *
 */
public class ConnectionSettings
{
	public static String host = "127.0.0.1";
	public static int port = 4433;
	public static void load()
	{
		File file = new File("server.txt");
		if(file.exists())
			if(file.isFile())
			{
				try
				{
					FileInputStream inputStream = new FileInputStream(file);
					InputStreamReader reader = new InputStreamReader(inputStream);
					BufferedReader bufferedReader = new BufferedReader(reader);
					String newHost = bufferedReader.readLine();
					if (!newHost.equals(""))
						host = newHost;
				} catch (FileNotFoundException e)
				{
				} catch (IOException e)
				{
				}
				
			}
				
	}

}
