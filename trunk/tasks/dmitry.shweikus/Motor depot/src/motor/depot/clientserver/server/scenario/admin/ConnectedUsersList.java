/**
 * 
 */
package motor.depot.clientserver.server.scenario.admin;

import java.util.ArrayList;

import motor.depot.clientserver.server.ClientThread;
import motor.depot.clientserver.server.Server;
import motor.depot.clientserver.server.scenario.tables.ITableProvider;
import motor.depot.model.User;

class ConnectedUsersList implements ITableProvider
{
	private ArrayList<User> users = new ArrayList<User>();
	/**
	 * @return the users
	 */
	public ArrayList<User> getUsers()
	{
		return users;
	}

	public ConnectedUsersList() {
		for (ClientThread clientThread : Server.getInstance().threads)
		{
			if (clientThread.getUser() != null)
				users.add(clientThread.getUser());
		}
	}
	
	@Override
	public int getColCount()
	{
		return 1;
	}

	@Override
	public int getRowCount()
	{
		return users.size();
	}

	@Override
	public String getColName(int col)
	{
		return "Name";
	}

	@Override
	public String getCellValue(int row, int col)
	{
		return users.get(row).getLogin();
	}
	
}