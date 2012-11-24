/**
 * 
 */
package motor.depot.clientserver.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.clientserver.server.scenario.admin.DispatcherMainScenario;
import motor.depot.clientserver.server.scenario.users.DriverMainScenario;
import motor.depot.clientserver.server.scenario.users.LoginScenario;
import motor.depot.model.Dispatcher;
import motor.depot.model.User;

public class ClientThread extends Thread
{
	private static final Logger LOGGER = Logger.getLogger(ClientThread.class);
	private Socket socket = null;
	private String sessionLogin = "<not defined>";
	private String address = "";
	private DataInputStream in = null;
	private User user = null;
	/**
	 * @return the socket
	 */
	public Socket getSocket()
	{
		return socket;
	}
	private DataOutputStream out = null;
	int threadId = -1;
	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	/**
	 * @return the in
	 */
	public DataInputStream getIn()
	{
		return in;
	}

	/**
	 * @return the out
	 */
	public DataOutputStream getOut()
	{
		return out;
	}

	/**
	 * @param fromClient
	 * @param threadId
	 * @param server TODO
	 */
	public ClientThread(Socket fromClient, int threadId) {
		super();
		this.socket = fromClient;
		this.threadId = threadId;
		address = socket.getInetAddress().toString();
	}

	@Override
	public String toString()
	{
		return "id:" + threadId + " address:" + address + " user:" + sessionLogin;
	}
	
	private void updateName()
	{
		String newName = threadId + "  " +  address + " " + sessionLogin;
		setName(newName);
	}
	@Override
	public void run()
	{
		try
		{
			updateName();
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			LoginScenario loginScenario = new LoginScenario(this);
			loginScenario.run();
			if (loginScenario.isLogged())
			{
				user = loginScenario.getUser();
				sessionLogin = user.getLogin();
				AbstractScenario menu;
				updateName();
				if (loginScenario.getUser() instanceof Dispatcher)
					menu = new DispatcherMainScenario(this);
				else
					menu = new DriverMainScenario(this);
				menu.run();
			}
			Server.getInstance().removeThreadFromQueue(this);
			LOGGER.debug("Thread [" + toString() + "] done.");
			if (!socket.isClosed())
			{
				out.close();
				in.close();
				socket.close();
			}
		} catch (IOException e)
		{
			LOGGER.debug("IOException by getting socket stream"); //$NON-NLS-1$
		}
	}
}