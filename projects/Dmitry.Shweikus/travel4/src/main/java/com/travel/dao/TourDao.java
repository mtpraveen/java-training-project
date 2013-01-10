/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.travel.db.ApplicationException;
import com.travel.enums.TransportKind;
import com.travel.enums.TravelKind;
import com.travel.pojo.Tour;

/**
 * @author dima
 * 
 */
public class TourDao extends BaseDao<Tour>
{
	private final String[] columns = new String[] { "name", "transport_kind", "travel_kind",
			"description", "required_documents", "days_count" };

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

	@Override
	public Tour newEntity()
	{
		return new Tour();
	}

	@Override
	public void readDataFromResultSet(Tour obj, ResultSet rs) throws SQLException
	{
		obj.setName(rs.getString(getColumn(0)));
		obj.setTransportKind(TransportKind.valueOf(rs.getString(getColumn(1))));
		obj.setTravelKind(TravelKind.valueOf(rs.getString(getColumn(2))));
		obj.setDescription(rs.getString(getColumn(3)));
		obj.setRequiredDocuments(rs.getString(getColumn(4)));
		obj.setDaysCount(rs.getInt(getColumn(5)));

	}

	@Override
	public void saveDataToPreparedStatement(Tour obj, PreparedStatement ps) throws SQLException
	{
		ps.setString(1, obj.getName());
		ps.setString(2, obj.getTransportKind().toString());
		ps.setString(3, obj.getTravelKind().toString());
		ps.setString(4, obj.getDescription());
		ps.setString(5, obj.getRequiredDocuments());
		ps.setInt(6, obj.getDaysCount());
	}

	public Tour create(String name, TransportKind transport_kind, TravelKind travel_kind,
			String description, String required_documents, int days_count)
			throws ApplicationException
	{
		return createConcrete(new Object[] { name, transport_kind.toString(),
				travel_kind.toString(), description, required_documents, days_count });
	}

}
