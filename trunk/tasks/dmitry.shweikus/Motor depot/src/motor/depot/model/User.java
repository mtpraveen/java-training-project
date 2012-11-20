/**
 * 
 */
package motor.depot.model;

import motor.depot.storages.interfaces.AbstractItemStateLoader;
import motor.depot.storages.interfaces.AbstractItemStateSaver;
import motor.depot.storages.interfaces.AbstractStorage;
import motor.depot.storages.interfaces.ICanBeSaved;
import motor.depot.storages.interfaces.ITableRowProvider;

/**
 * @author dima
 *
 */
public abstract class User implements ICanBeSaved,ITableRowProvider
{
	int id;
	String login;
	String password;
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * @return the login
	 */
	public String getLogin()
	{
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login)
	{
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	public abstract boolean isAdmin();
	
	@Override
	public AbstractItemStateSaver getSaver(
			AbstractStorage saverCreator) {
		return saverCreator.createNewSaver(getClassId()).addValue(id).addValue(login).addValue(password);
	}
	
	@Override
	public void loadObjects(MotorDepot motorDepot,
			AbstractItemStateLoader getter) {
		//do nothing here because we have no objects		
	}
	
	@Override
	public void loadPrimitives(AbstractItemStateLoader stateGetter) {
		id = stateGetter.getValueInt(0);
		login = stateGetter.getValue(1);
		password = stateGetter.getValue(2);
	}
	
	public boolean mathLoginData(String login, String password)
	{
		return this.login.equals(login) && this.password.equals(password);
	}
	@Override
	public ITableRowProvider getRowProvider()
	{
		return this;
	}
	@Override
	public int getColCount()
	{
		return 1;
	}
	@Override
	public String getColName(int col)
	{
		return "Name";
	}
	@Override
	public String getValue(int col)
	{
		return login;
	}
}
