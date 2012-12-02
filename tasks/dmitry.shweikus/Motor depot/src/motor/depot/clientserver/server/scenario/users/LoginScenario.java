/**
 * 
 */
package motor.depot.clientserver.server.scenario.users;

import java.io.IOException;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.Server;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.model.MotorDepot;
import motor.depot.model.User;

/**
 * @author dima
 * 
 */
public class LoginScenario extends AbstractScenario
{
	/**
	 * @param thread
	 */
	public LoginScenario(ClientThread thread) {
		super(thread);
	}

	private User user = null;

	/**
	 * @return the logged
	 */
	public boolean isLogged()
	{
		return user != null;
	}

	/**
	 * @return the user
	 */
	public User getUser()
	{
		return user;
	}

	@Override
	public void run() throws IOException
	{
		str(thread.getString("Please_login")); //$NON-NLS-1$
		while (!isLogged())
		{
			str(thread.getString("Login")); //$NON-NLS-1$
			String login = readString();
			str(thread.getString("Password")); //$NON-NLS-1$
			String password = readPassword();
			user = thread.getMotorDepot().findUser(login, password);
			if (isLogged())
			{
				for (ClientThread clientThread : Server.getInstance().threads)
				{
					if (clientThread != thread)
					{
						if (user == clientThread.getUser())
						{
							user = null;
							if (question(thread.getString("This_user_allready_connected.Try_agayn"))) //$NON-NLS-1$
								break;
							else
								return;
						}
					}
				}
			}
			else if (!question(thread.getString("Invalid_password_or_login.Try_agayn"))) //$NON-NLS-1$
				break;
		}
	}
}
