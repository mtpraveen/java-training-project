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
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.Client;
import com.travel.pojo.Tour;

/**
 * @author dima
 * 
 */
public class TourDao extends BaseDao<Tour>
{
	private final String[] columns = new String[] { "name", "transportKind", "travelKind",
			"description", "requiredDocuments", "daysCount" };

	@Override
	public String getTableName()
	{
		return "tours";
	}

	@Override
	public String[] getColumnNames()
	{
		return columns;
	}

	public Tour create(String name, TransportKind transport_kind, TravelKind travel_kind,
			String description, String required_documents, int days_count)
			throws DbSqlException
	{
		Tour tour = new Tour();
		tour.setDaysCount(days_count);
		tour.setDescription(description);
		tour.setName(name);
		tour.setRequiredDocuments(required_documents);
		tour.setTransportKind(transport_kind);
		tour.setTravelKind(travel_kind);
		return create(tour);
	}
	
	public List<Tour> findByName(String name) throws DbSqlException
	{
		Session session = ConnectionManager.getHibernateSession();
		Criteria criteria = session.createCriteria(Tour.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}

	@Override
	public Class<Tour> getEntityClass()
	{
		return Tour.class;
	}

}
