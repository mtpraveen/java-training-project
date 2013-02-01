/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;

/**
 * @author dima
 *
 */
public class ClientDao extends BaseDao<Client>
{
	private final String[] columns = new String[]{"firstname","lastname","document1","document2","document3","document4","description"};
	@Override
	public String getTableName()
	{
		return "clients";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	public Client create(String firstname, String lastname, String document1, String document2, String document3, String document4, String description) throws DbSqlException
	{
		Client client = new Client();
		client.setFirstName(firstname);
		client.setLastName(lastname);
		client.setDocument1(document1);
		client.setDocument2(document2);
		client.setDocument3(document3);
		client.setDocument4(document4);
		client.setDescription(description);
		return create(client);
	}
	public List<Client> findByName(String firstName,String lastName)
	{
		Session session = ConnectionManager.getHibernateSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq("firstName", firstName));
		criteria.add(Restrictions.eq("lastName", lastName));
		return criteria.list();
	}

	@Override
	public Class<Client> getEntityClass()
	{
		return Client.class;
	}
}
