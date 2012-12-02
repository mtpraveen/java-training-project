/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import java.io.IOException;

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

	private String getUsername() throws IOException
	{
		String res = null;
		while(res == null)
		{
			str(thread.getString("AddUserScenario.Username")); //$NON-NLS-1$
			String s = readString();
			if(s.equals("")) //$NON-NLS-1$
			{
				if (question(thread.getString("AddUserScenario.Name_cannot_be_empty.Abort_action"))) //$NON-NLS-1$
					return null;
			}
			else
			{
				User user = thread.getMotorDepot().findUserIgnoreCase(s);
				if (user != null)
				{
					if (question(thread.getString("AddUserScenario.User_with_this_name_allready_exists.Abort_action"))) //$NON-NLS-1$
						return null;
				}
				else
					res = s;
			}
		}
		return res;
	}
	@Override
	public void run() throws IOException
	{
		String userName = getUsername();
		if (userName != null)
		{
			str(thread.getString("AddUserScenario.Password")); //$NON-NLS-1$
			String password = readPassword();
			thread.getMotorDepot().addUser(userName,password);
			str(thread.getString("AddUserScenario.User_created")); //$NON-NLS-1$
		}
	}

}
