/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.scenario.AbstractScenario;

/**
 * @author dima
 *
 */
public class SetLocaleScenario extends AbstractScenario
{

	public SetLocaleScenario(ClientThread thread) {
		super(thread);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run()
	{
		while(true)
		{
			str("Enter '1' if you want use english language");
			str("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 '2' \u0435\u0441\u043B\u0438 \u0432\u044B \u0445\u043E\u0442\u0438\u0442\u0435 \u0438\u0441\u043F\u043E\u043B\u044C\u0437\u043E\u0432\u0430\u0442\u044C \u0440\u0443\u0441\u0441\u043A\u0438\u0439 \u044F\u0437\u044B\u043A");
			String s = readString();
			str("");
			if(s.equals("1"))
				thread.setEnglishLocale();
			else if(s.equals("2"))
				thread.setRussianLocale();
			else 
				continue;
			return;
		}
	}

}
