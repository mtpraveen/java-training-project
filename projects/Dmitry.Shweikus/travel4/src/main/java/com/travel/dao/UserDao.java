/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.travel.dao.utils.SqlQueryDaoFacade.SqlQueryDaoFacadeResultItem;
import com.travel.db.ApplicationException;
import com.travel.pojo.User;

/**
 * @author dima
 *
 */
public class UserDao extends BaseDao<User>
{
	private final String[] columns = new String[]{"name","login","password","admin"};
	@Override
	public String getTableName()
	{
		return "users";
	}

	@Override
	public User newEntity()
	{
		return new User();
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	@Override
	public void readDataFromResultSet(User obj, ResultSet rs) throws SQLException
	{
		obj.setName(rs.getString(getColumn(0)));
		obj.setLogin(rs.getString(getColumn(1)));
		obj.setPassword(rs.getString(getColumn(2)));
		obj.setAdmin(rs.getBoolean(getColumn(3)));
	}

	@Override
	public void saveDataToPreparedStatement(User obj, PreparedStatement ps) throws SQLException
	{
		ps.setString(1, obj.getName());
		ps.setString(2, obj.getLogin());
		ps.setString(3, obj.getPassword());
		ps.setBoolean(4, obj.isAdmin());
	}
	
	public User createUser(String name, String login, String password, boolean admin) throws ApplicationException
	{
		Object[] data = new Object[]{name, login, password, admin};
		return createConcrete(data);
	}
	
	public User findUserByLoginAndPassword(String login, String password) throws ApplicationException
	{
		String condition = "WHERE (login = ?) and (password = ?)";
		List params = new ArrayList();
		params.add(login);
		params.add(password);
		for (User user : findAllWithCondition(condition, params))
		{
			return user;
		}
		return null;
	}
}
