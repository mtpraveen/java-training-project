package motor.depot.clientserver.server.scenario;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import motor.depot.clientserver.server.ClientThread;

public class MenuScenario extends AbstractScenario {
	/**
	 * @param thread
	 */
	public MenuScenario(ClientThread thread) {
		super(thread);
	}

	private ArrayList<MenuItem> menuItems = new ArrayList<MenuScenario.MenuItem>();
	private boolean repeatable = false;

	/**
	 * @return the repeatable
	 */
	public boolean isRepeatable() {
		return repeatable;
	}

	/**
	 * @param repeatable the repeatable to set
	 */
	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	private class MenuItem {
		String rext;
		AbstractScenario scenario;

		/**
		 * @param text
		 * @param scenario
		 */
		public MenuItem(String text, AbstractScenario scenario) {
			this.rext = text;
			this.scenario = scenario;
		}
	}

	public void addMenuItem(String text, AbstractScenario scenario) {
		menuItems.add(new MenuItem(text, scenario));
	}

	public void addExitItem(String text) {
		menuItems.add(new MenuItem(text, null));
	}

	@Override
	public void run() {
		while (true)
		{
			str("");
			str("Please enter action number:");
			for(int i = 0; i<menuItems.size(); i++)
			{
				str((i+1) + ". " + menuItems.get(i).rext);
			}
			Integer iAnswer = null;
			while(iAnswer == null)
			{
				String answer = readString();
				if (isInt(answer))
				{
					int intAnswer = Integer.parseInt(answer);
					if (intAnswer>0)
						if (intAnswer <= menuItems.size())
							iAnswer = intAnswer;
				}
				if (iAnswer == null)
					str("Please enter valid number.");
			}
			MenuItem menuItem = menuItems.get(iAnswer-1);
			if (menuItem.scenario == null)
				return;
			else
				menuItem.scenario.run();
			if (!repeatable)
				return;
		}
	}
}
