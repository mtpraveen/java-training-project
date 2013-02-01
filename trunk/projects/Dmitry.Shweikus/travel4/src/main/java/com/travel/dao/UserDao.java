/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Tour;
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
	public String[] getColumnNames()
	{
		return columns;
	}

	public User createUser(String name, String login, String password, boolean admin) 
	{
		User user = new User();
		user.setAdmin(admin);
		user.setLogin(login);
		user.setName(name);
		user.setPassword(password);
		return create(user);
	}
	
	public User findUserByLoginAndPassword(String login, String password) throws DbSqlException
	{
		Session session = ConnectionManager.getHibernateSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		criteria.add(Restrictions.eq("password", password));
		List<User> users = criteria.list();
		if(users.size() == 0)
			return null;
		else
			return users.get(0);
	}
	public List<User> findUsersByLogin(String login) throws DbSqlException
	{
		Session session = ConnectionManager.getHibernateSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("login", login));
		return criteria.list();
	}

	@Override
	public Class<User> getEntityClass()
	{
		return User.class;
	}
}
