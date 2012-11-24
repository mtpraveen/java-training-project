/**
 * 
 */
package motor.depot.clientserver.server.scenario.users;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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
	public void run()
	{
		str("Please login");
		while (!isLogged())
		{
			str("Login");
			String login = readString();
			str("Password");
			String password = readPassword();
			user = MotorDepot.getInstance().findUser(login, password);
			if (isLogged())
			{
				for (ClientThread clientThread : Server.getInstance().threads)
				{
					if (clientThread != thread)
					{
						if (user == clientThread.getUser())
						{
							user = null;
							if (question("This user allready connected. Try agayn?"))
								break;
							else
								return;
						}
					}
				}
			}
			else if (!question("Invalid password or login. Try agayn?"))
				break;
		}
	}
}
