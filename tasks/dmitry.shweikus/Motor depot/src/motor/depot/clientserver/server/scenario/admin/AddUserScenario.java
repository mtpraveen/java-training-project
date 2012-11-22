/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;
import motor.depot.model.MotorDepot;
import motor.depot.model.User;

/**
 * @author dima
 *
 */
public class AddUserScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public AddUserScenario(ClientThread thread) {
		super(thread);
	}

	private String getUsername()
	{
		String res = null;
		while(res == null)
		{
			str("Username:");
			String s = readString();
			if(s.equals(""))
			{
				if (question("Name cannot be empty. Abort action?"))
					return null;
			}
			else
			{
				User user = MotorDepot.getInstance().findUserIgnoreCase(s);
				if (user != null)
				{
					if (question("User with this name allready exists. Abort action?"))
						return null;
				}
				else
					res = s;
			}
		}
		return res;
	}
	@Override
	public void run()
	{
		String userName = getUsername();
		if (userName != null)
		{
			str("Password:");
			String password = readPassword();
			MotorDepot.getInstance().addUser(userName,password);
			str("User created");
		}
	}

}
