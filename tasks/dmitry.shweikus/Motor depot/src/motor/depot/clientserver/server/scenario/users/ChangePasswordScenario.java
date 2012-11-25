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
		str(thread.getString("ChangePasswordScenario.Enter_new_password")); //$NON-NLS-1$
		String password1 = readPassword();
		str(thread.getString("ChangePasswordScenario.Confirm_password")); //$NON-NLS-1$
		String password2 = readPassword();
		if (password1.equals(password2))
		{
			thread.getUser().setLogin(password1);
			str(thread.getString("ChangePasswordScenario.Password_changed")); //$NON-NLS-1$
		}
		else
		{
			str(thread.getString("ChangePasswordScenario.Password_not_match")); //$NON-NLS-1$
			waitForInput();
		}
	}
}
