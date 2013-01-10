/**
 * 
 */
package com.travel.dao.utils;

import java.util.ArrayList;

import com.travel.dao.BaseDao;
import com.travel.web.utils.TravelConsts;

/**
 * @author dima
 * 
 */
public class ManyToOneConditionConstrainBuilder
{
	private ArrayList<String> conditions = new ArrayList<>();

	public void addConstrain(BaseDao master, BaseDao detail)
	{
		String masterField = TravelConsts.evalSingleItem(master.getTableName()).replace("_", "");
		String condition = detail.getTableName() + ".id_" + masterField + " = "
				+ master.getIdColumn();
		conditions.add(condition);
	}

	public void addConstrainWithId(BaseDao master, BaseDao detail, long masterId)
	{
		String masterField = TravelConsts.evalSingleItem(master.getTableName()).replace("_", "");
		String condition = detail.getTableName() + ".id_" + masterField + " = " + masterId;
		conditions.add(condition);
	}
	
	public void addFindByIdContrain(BaseDao dao, long id)
	{
		conditions.add(dao.getIdColumn() + " = " + id);
	}

	public String build()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < conditions.size(); i++)
		{
			if (i > 0)
				builder.append(" AND ");
			builder.append(" (" + conditions.get(i) + ") ");
		}
		return builder.toString();
	}
}
