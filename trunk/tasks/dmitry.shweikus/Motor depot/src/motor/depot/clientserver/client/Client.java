/**
 * 
 */
package motor.depot.clientserver.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import motor.depot.clientserver.ClientServerCommand;
import motor.depot.clientserver.CloseClientCommandImpl;
import motor.depot.clientserver.ConnectionSettings;
import motor.depot.clientserver.IExitable;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * @author dima
 * 
 */
public class Client implements IExitable
{
	private static final Logger LOGGER = Logger.getLogger(Client.class);
	DataInputStream srvIn = null;
	DataOutputStream srvOut = null;
	Socket fromserver = null;
	ClientMessageParserThread clientMessageParserThread;
	private class ClientMessageParserThread extends Thread
	{
		@Override
		public void run()
		{
			String fserver = ""; 
			try
			{
				while((fserver = srvIn.readUTF()) != null)
				{
					ClientServerCommand cmd = ClientServerCommand.valueOf(fserver);
					if (cmd != null)
					{
						cmd.getImpl().processDataOnClient(srvIn, srvOut);
					}
				}
			} catch (IOException e)
			{
				//IOException occurs when we reading from closed socket.
				//whe don't need handle it
			}
		}
	}
	
	public void execute() 
	{
		try
		{
			CloseClientCommandImpl.setExitable(this);
			LOGGER.debug("Client started");
			ConnectionSettings.load();
			fromserver = new Socket(ConnectionSettings.host, ConnectionSettings.port);
			LOGGER.debug("Client connected to " + ConnectionSettings.host + ":" + ConnectionSettings.port);
			srvIn = new DataInputStream(fromserver.getInputStream());
			srvOut = new DataOutputStream(fromserver.getOutputStream());
			clientMessageParserThread = new ClientMessageParserThread();
			clientMessageParserThread.start();
			clientMessageParserThread.join();
			LOGGER.debug("Closing socket and streams");
		}catch (IOException e)
		{
			LOGGER.error("IOException error occured!", e);
		} catch (InterruptedException e)
		{
			LOGGER.error("InterruptedException error occured!", e);
		} 
	}
	@Override
	public void doExit() 
	{
		LOGGER.debug("Exit event occured");
		try
		{
			srvOut.close(); 
			srvIn.close();
			fromserver.close(); 
		} catch (IOException e)
		{
			LOGGER.error("IOException by closing socket before exiting",e);
		}
	}
	public static void main(String[] args) 
	{
		PropertyConfigurator.configure("log4j.properties");
		Client cl = new Client();
		cl.execute();
		LOGGER.debug("Client closed");
	}
}
