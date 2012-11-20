/**
 * 
 */
package motor.depot.clientserver.server.scenario.users;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;

/**
 * @author dima
 *
 */
public class ChangePasswordScenario extends AbstractScenario
{

	/**
	 * @param thread
	 */
	public ChangePasswordScenario(ClientThread thread) {
		super(thread);
	}

	@Override
	public void run()
	{
		str("Enter new password:");
		String password1 = readPassword();
		str("Confirm password:");
		String password2 = readPassword();
		if (password1.equals(password2))
		{
			thread.getUser().setLogin(password1);
			str("Password changed");
		}
		else
		{
			str("Password not match");
			waitForInput();
		}
	}
}
