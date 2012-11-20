/**
 * 
 */
package motor.depot.clientserver;

import java.io.BufferedReader;
import java.io.Console;
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
			if(console == null)
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
	@Override
	public void processDataOnClient(BufferedReader fromServer, PrintWriter toServer)
	{
		try
		{
			this.toServer = toServer;
			isPassword = fromServer.readLine().equals("1");
			Thread thread = new Thread(new WaitTextThread());
			thread.setDaemon(true);
			thread.start();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
		}
	}
	@Override
	public void sendToClient(PrintWriter toClient)
	{
		sendToClient(toClient, false);
	}
	
	public void sendToClient(PrintWriter toClient, boolean isPassword)
	{
		super.sendToClient(toClient);
		if(isPassword)
			toClient.println("1");
		else
			toClient.println("0");
	}
}