/**
 * 
 */
package com.travel.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	@Override
	public Client newEntity()
	{
		return new Client();
	}

	@Override
	public void readDataFromResultSet(Client obj, ResultSet rs) throws SQLException
	{
		obj.setFirstName(rs.getString(getColumn(0)));
		obj.setLastName(rs.getString(getColumn(1)));
		obj.setDocument1(rs.getString(getColumn(2)));
		obj.setDocument2(rs.getString(getColumn(3)));
		obj.setDocument3(rs.getString(getColumn(4)));
		obj.setDocument4(rs.getString(getColumn(5)));
		obj.setDescription(rs.getString(getColumn(6)));
	}

	@Override
	public void saveDataToPreparedStatement(Client obj, PreparedStatement ps)
			throws SQLException
	{
		ps.setString(1, obj.getFirstName());
		ps.setString(2, obj.getLastName());
		ps.setString(3, obj.getDocument1());
		ps.setString(4, obj.getDocument2());
		ps.setString(5, obj.getDocument3());
		ps.setString(6, obj.getDocument4());
		ps.setString(7, obj.getDescription());
	}
	public Client create(String firstname, String lastname, String document1, String document2, String document3, String document4, String description) throws DbSqlException
	{
		return createConcrete(new Object[]{firstname,lastname,document1,document2,document3,document4,description});
	}
}
