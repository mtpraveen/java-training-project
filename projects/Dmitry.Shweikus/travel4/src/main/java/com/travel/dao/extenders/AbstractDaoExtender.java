/**
 * 
 */
package com.travel.dao.extenders;

import java.util.List;
import java.util.ArrayList;

import com.travel.dao.BaseDao;
import com.travel.dao.utils.SqlConstrainBuilder;
import com.travel.dao.utils.SelectSqlExecutor;
import com.travel.dao.utils.SelectSqlExecutor.SqlQueryDaoFacadeResultItem;
import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
/**
 * @author dima
 *
 */
public abstract class AbstractDaoExtender<T extends AbstractExtender>
{
	protected List<T> executeQuery(String additionalConstrains,List params) throws DbSqlException
	{
		SelectSqlExecutor sql = new SelectSqlExecutor(ConnectionManager.getInstance().getConnection());
		
		List<BaseDao> daos = new ArrayList<>();
		daos.add(getMainDao());
		daos.addAll(getOthersDaos());
		
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		fillConstrainBuilder(constrainBuilder);
		String constrains = constrainBuilder.build();
		if (additionalConstrains!=null)
			if(!additionalConstrains.equals(""))
			{
				constrains += " AND (" + additionalConstrains + ")";
			}
		List<T> extenders = new ArrayList<>();
		List<SqlQueryDaoFacadeResultItem> resultItems = sql.executeQuery(daos, constrains, params);
		for (SqlQueryDaoFacadeResultItem item : resultItems)
		{
			T extender = newExtender();
			fillExtenderFields(item, extender);
			extenders.add(extender);
		}
		return extenders;
	}
	public List<T> findAll() throws DbSqlException
	{
		return executeQuery(null,null);
	}
	public T findById(long id) throws DbSqlException
	{
		SqlConstrainBuilder constrainBuilder = new SqlConstrainBuilder();
		constrainBuilder.addFindByIdContrain(getMainDao(), id);
		List<T> results = executeQuery(constrainBuilder.build(), null);
		if(results.size() == 0)
			return null;
		else
			return results.get(0);
	}
	protected abstract void fillConstrainBuilder(SqlConstrainBuilder conditionConstrainBuilder);
	protected abstract T newExtender();
	protected abstract BaseDao getMainDao();
	protected abstract List<BaseDao> getOthersDaos();
	protected abstract void fillExtenderFields(SqlQueryDaoFacadeResultItem item, T extender);
}
