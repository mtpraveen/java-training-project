/**
 * 
 */
package com.travel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.travel.dao.utils.SelectSqlExecutor;
import com.travel.dao.utils.SelectSqlExecutor.SqlQueryDaoFacadeResultItem;
import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.BaseEntity;

/**
 * @author dima
 *
 */
public abstract class BaseDao<T extends BaseEntity>
{
	//abstract classes depends from each entity
	public abstract String getTableName();
	//all columns except column "id"
	public abstract String[] getColumnNames();
	public abstract T newEntity();
	public abstract void readDataFromResultSet(T obj,ResultSet rs) throws SQLException;
	public abstract void saveDataToPreparedStatement(T obj,PreparedStatement ps) throws SQLException;
	//
	public String getColumn(int index)
	{
		return getTableName() + "." + getColumnNames()[index];
	}
	public String getIdColumn()
	{
		return getTableName() + ".id";
	}
	public Connection getConnection() throws DbSqlException
	{
		return ConnectionManager.getInstance().getConnection();
	}
	//statements builders
	private String buildInsertSqlStatement()
	{
		StringBuilder data = new StringBuilder("INSERT INTO `" + getTableName() + "`");
		data.append(" (");
		String[] columns = getColumnNames();
		for (String column : columns)
		{
			data.append("`").append(column).append("`, ");
		}
		data.append("id) VALUES (");
		for(int i = 0;i<columns.length;i++)
		{
			data.append("?,");
		}
		data.append("NULL)");
			
		return data.toString();
	}
	private String buildSelectSqlStatement()
	{
		StringBuilder data = new StringBuilder("SELECT ");
		String[] columns = getColumnNames();
		for (String column : columns)
		{
			data.append("`").append(column).append("`, ");
		}
		data.append("id FROM `").append(getTableName()).append("` ");
		return data.toString();
	}
	private String buildUpdateSqlStatement()
	{
		StringBuilder data = new StringBuilder();
		data.append("UPDATE `").append(getTableName()).append("` SET ");
		boolean firstValue = true;
		String[] columns = getColumnNames();
		for (String column : columns)
		{
			if(firstValue)
			{
				firstValue = false;
			}
			else
			{
				data.append(", ");
			};
			data.append("`").append(column).append("` = ?");
		}
		data.append(" WHERE id = ?");
		return data.toString();
	}
	private String buildDeleteSqlStatement()
	{
		return "DELETE FROM `" + getTableName() + "`";
	}
	//===================================
	//===================================
	//===================================
	//base actions
	//===================================
	//===================================
	//===================================
	private BaseEntity createBaseModel(Object[] values) throws DbSqlException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
		String query = buildInsertSqlStatement(); 
		try {
			Connection conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(query);
			for(int i = 0;i<values.length;i++)
			{
				ps.setObject(i+1, values[i]);
			}
			int res = ps.executeUpdate();
			if (res != 0) {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT LAST_INSERT_ID()");
				if (rs.next()) {
					return findById(rs.getLong(1));
				}
			}

		} catch (SQLException e) {
			throw new DbSqlException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	private T createFromObject(T base) throws DbSqlException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
		String query = buildInsertSqlStatement(); 
		try {
			Connection conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(query);
			saveDataToPreparedStatement(base, ps);
			int res = ps.executeUpdate();
			if (res != 0) {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT LAST_INSERT_ID()");
				if (rs.next()) {
					return findById(rs.getLong(1));
				}
			}

		} catch (SQLException e) {
			throw new DbSqlException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	protected List<T> findAllWithCondition(String condition,List params) throws DbSqlException
	{
		String sqlStatement = buildSelectSqlStatement();
		if(condition != null)
			if (!condition.equals(""))
			{
				if(condition.trim().toLowerCase().indexOf("where") != 0)
				{
					condition = "WHERE (" + condition + ")";
				}
				sqlStatement += condition;
			}
		List<T> result=new ArrayList<T>();
		SelectSqlExecutor daoFacade = new SelectSqlExecutor(ConnectionManager.getInstance().getConnection());
		List<SqlQueryDaoFacadeResultItem> tmp = daoFacade.executeQuery(this, condition, params);
		for (SqlQueryDaoFacadeResultItem sqlQueryDaoFacadeResultItem : tmp)
		{
			T entity = (T)sqlQueryDaoFacadeResultItem.getEntitys().get(0);
			result.add(entity);
		}
		return result;
	}

	public boolean update(T obj) throws DbSqlException
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(buildUpdateSqlStatement());
			saveDataToPreparedStatement(obj, ps);
			ps.setLong(getColumnNames().length+1, obj.getId());
			return ps.executeUpdate() != 0;
		} catch (SQLException e) {
			throw new DbSqlException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean deleteAllWithCondion(String condition) throws DbSqlException
	{
		String sqlStatement = buildDeleteSqlStatement(); 
		if(condition != null)
			if (!condition.equals(""))
			{
				if(condition.trim().toLowerCase().indexOf("where") != 0)
				{
					condition = "WHERE (" + condition + ")";
				}
				sqlStatement += condition;
			}
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection conn = ConnectionManager.getInstance().getConnection();
			ps = conn.prepareStatement(sqlStatement);
			int res = ps.executeUpdate();
			return res != 0;
		} catch (SQLException e) {
			throw new DbSqlException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//===================================
	//===================================
	//===================================
	//other utilites
	//===================================
	//===================================
	//===================================
	@SuppressWarnings("unchecked")
	protected T createConcrete(Object[] values) throws DbSqlException
	{
		return (T)createBaseModel(values);
	}
	public T create(T entity) throws DbSqlException
	{
		return createFromObject(entity);
	}

	
	public T findById(long l) throws DbSqlException
	{
		List<T> res = findAllWithCondition("id = " + l,null);
		if (res.size() == 0)
			return null;
		else
			return res.get(0);
	}
	public boolean delete(long l) throws DbSqlException
	{
		return deleteAllWithCondion("id = " + l);
	}
	public boolean delete(T obj) throws DbSqlException
	{
		if (obj != null)
			return deleteAllWithCondion("id = " + obj.getId());
		else
			return false;
	}

	public boolean deleteAll() throws DbSqlException
	{
		return deleteAllWithCondion("");
	}
	
	public List<T> findAll() throws DbSqlException
	{
		return findAllWithCondition("",null);
	}
}
