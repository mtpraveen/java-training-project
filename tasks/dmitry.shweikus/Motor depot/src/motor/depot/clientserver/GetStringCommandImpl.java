/**
 * 
 */
package motor.depot.clientserver;

import java.io.BufferedReader;
import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author dima
 * 
 */
public class GetStringCommandImpl extends AbstractClientServerCommandImpl
{
	boolean isPassword;

	/**
	 * @param command
	 */
	public GetStringCommandImpl(ClientServerCommand command) {
		super(command);
	}

	PrintWriter toServer = null;

	private class WaitTextThread implements Runnable
	{
		@Override
		public void run()
		{
			String text;
			Console console = System.console();
			if (console == null)
			{
				Scanner scanner = new Scanner(System.in);
				text = scanner.nextLine();
			}
			else if (isPassword)
			{
				text = new String(console.readPassword());
			}
			else
			{
				text = console.readLine();
			}
			toServer.println(text);
		}
	}

	private void sendFile(String fileName)
	{
		File file = new File(fileName);
		if (file.exists())
			if(file.isFile())
			{
				try
				{
					DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
					return;
				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		toServer.println("-1");
	}

	@Override
	public void processDataOnClient(BufferedReader fromServer, PrintWriter toServer)
	{
		try
		{
			this.toServer = toServer;
			int iKind = Integer.parseInt(fromServer.readLine());
			CLIENT_CONTENT_KIND kind = CLIENT_CONTENT_KIND.TEXT;
			try
			{
				kind = CLIENT_CONTENT_KIND.values()[iKind];
			} catch (Exception e)
			{
			}
			if (kind == CLIENT_CONTENT_KIND.FILE)
			{
				sendFile(fromServer.readLine());
			}
			else
			{
				isPassword = kind == CLIENT_CONTENT_KIND.PASSWORD;
				Thread thread = new Thread(new WaitTextThread());
				thread.setDaemon(true);
				thread.start();
			}
		} catch (IOException e)
		{
		}
	}

	@Override
	public void sendToClient(PrintWriter toClient) 
	{
		try
		{
			sendToClient(toClient, CLIENT_CONTENT_KIND.TEXT, "");
		} catch (IOException e)
		{
		}
	}

	public void sendToClient(PrintWriter toClient, CLIENT_CONTENT_KIND content_KIND, String filepath) throws IOException
	{
		super.sendToClient(toClient);
		toClient.println(String.valueOf(content_KIND.ordinal()));
		if (content_KIND == CLIENT_CONTENT_KIND.FILE)
		{
			toClient.println(filepath);
		}
	}
}