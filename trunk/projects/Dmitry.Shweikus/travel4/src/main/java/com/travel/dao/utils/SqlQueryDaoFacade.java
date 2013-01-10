/**
 * 
 */
package com.travel.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.travel.dao.BaseDao;
import com.travel.db.ApplicationException;
import com.travel.pojo.BaseEntity;

/**
 * @author dima
 * 
 */
public class SqlQueryDaoFacade
{
	public static class SqlQueryDaoFacadeResultItem
	{
		private ArrayList<BaseEntity> entitys = new ArrayList<>();
		public ArrayList<BaseEntity> getEntitys()
		{
			return entitys;
		}
	}
	private Connection connection;
	public SqlQueryDaoFacade(Connection connection) {
		this.connection = connection;
	}
	private String getFields(List<BaseDao> daoList)
	{
		boolean isFirstDao = true;
		StringBuilder builder = new StringBuilder();
		for (BaseDao<BaseEntity> baseDao : daoList)
		{
			if(!isFirstDao)
				builder.append(",");
			isFirstDao = false;
			int colCount = baseDao.getColumnNames().length;
			for(int i = 0;i<colCount;i++)
			{
				if(i>0)
					builder.append(",");
				builder.append(baseDao.getColumn(i));
			}
			if (colCount>0)
				builder.append(",");
			builder.append(baseDao.getIdColumn());//id
		}
		return builder.toString();
	}
	private String getTables(List<BaseDao> daoList)
	{
		StringBuilder tablesBuilder = new StringBuilder();
		for (BaseDao<BaseEntity> baseDao : daoList)
		{
			if (tablesBuilder.length() > 0)
				tablesBuilder.append(",");
			tablesBuilder.append(baseDao.getTableName());
		}
		return tablesBuilder.toString();
	}
	private String buildSql(List<BaseDao> daoList,String conditions)
	{
		StringBuilder tablesBuilder = new StringBuilder();
		for (BaseDao<BaseEntity> baseDao : daoList)
		{
			if (tablesBuilder.length() > 0)
				tablesBuilder.append(",");
			tablesBuilder.append(baseDao.getTableName());
		}
		return "SELECT " + getFields(daoList) + " FROM " + getTables(daoList) + " " + conditions;
	}
	public List<SqlQueryDaoFacadeResultItem> executeQuery(
			List<BaseDao> daoList, String conditions,List params) throws ApplicationException
	{
		if(params == null)
			params = new ArrayList<Object>();
		PreparedStatement st = null;
		ResultSet rs = null;
		List<SqlQueryDaoFacadeResultItem> result=new ArrayList<>();
		String sql = "";
		try {
			sql = buildSql(daoList, conditions);
			st = connection.prepareStatement(sql);
			for(int i = 0;i<params.size();i++)
				st.setObject(i+1, params.get(i));
			rs = st.executeQuery();
			while (rs.next()) {
				SqlQueryDaoFacadeResultItem item = new SqlQueryDaoFacadeResultItem();
				for (BaseDao<BaseEntity> baseDao : daoList)
				{
					BaseEntity entity = baseDao.newEntity();
					baseDao.readDataFromResultSet(entity, rs);
					entity.setId(rs.getLong(baseDao.getIdColumn()));
					item.entitys.add(entity);
				}
				result.add(item);
			}
		} catch (SQLException e) {
			throw new ApplicationException(
			//"Can't select records from table(s) " + getTables(daoList) + "  due to malformed SQL statement!");
			"Can't execute sql [" + sql + "]  due to malformed SQL statement!");
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public List<SqlQueryDaoFacadeResultItem> executeQuery(
			BaseDao dao, String conditions,List params) throws ApplicationException
	{
		List<BaseDao> list = new ArrayList<>();
		list.add(dao);
		return executeQuery(list, conditions, params);
	}
}
