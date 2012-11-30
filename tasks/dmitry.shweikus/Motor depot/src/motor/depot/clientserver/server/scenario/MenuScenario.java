package motor.depot.clientserver.server.scenario;

import java.io.IOException;
import java.util.ArrayList;

import motor.depot.clientserver.server.ClientThread;

public class MenuScenario extends AbstractScenario {
	int selectedItem = -1;
	String caption = ""; //$NON-NLS-1$
	/**
	 * @return the caption
	 */
	public String getCaption()
	{
		return caption;
	}

	/**
	 * @param caption the caption to set
	 */
	public void setCaption(String caption)
	{
		this.caption = caption;
	}

	/**
	 * @return the selectedItem
	 */
	public int getSelectedItem()
	{
		return selectedItem;
	}

	/**
	 * @param thread
	 */
	public MenuScenario(ClientThread thread) {
		super(thread);
		caption = thread.getString("MenuScenario.Please_enter_action_number"); //$NON-NLS-1$
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
	public void run() throws IOException {
		while (true)
		{
			str(""); //$NON-NLS-1$
			str(caption);
			for(int i = 0; i<menuItems.size(); i++)
			{
				str((i+1) + ". " + menuItems.get(i).rext); //$NON-NLS-1$
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
					str(thread.getString("MenuScenario.Please_enter_valid_number")); //$NON-NLS-1$
			}
			MenuItem menuItem = menuItems.get(iAnswer-1);
			selectedItem = iAnswer - 1;
			if (menuItem.scenario == null)
				return;
			else
				menuItem.scenario.run();
			if (!repeatable)
				return;
		}
	}
}
