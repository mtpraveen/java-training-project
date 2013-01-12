/**
 * 
 */
package com.travel.dao.extenders;

import java.util.List;
import java.util.ArrayList;

import com.travel.dao.BaseDao;
import com.travel.dao.TourDao;
import com.travel.dao.TourSheduleDao;
import com.travel.dao.utils.SqlConstrainBuilder;
import com.travel.dao.utils.SelectSqlExecutor.SqlQueryDaoFacadeResultItem;
import com.travel.pojo.Tour;
import com.travel.pojo.TourShedule;

/**
 * @author dima
 *
 */
public class TourSheduleDaoExtender extends AbstractDaoExtender<TourSheduleExtender>
{
	@Override
	protected void fillConstrainBuilder(SqlConstrainBuilder conditionConstrainBuilder)
	{
		conditionConstrainBuilder.addConstrain(new TourDao(), new TourSheduleDao());
	}

	@Override
	protected TourSheduleExtender newExtender()
	{
		return new TourSheduleExtender();
	}

	@Override
	protected BaseDao getMainDao()
	{
		return new TourSheduleDao();
	}

	@Override
	protected List<BaseDao> getOthersDaos()
	{
		List<BaseDao> daos = new ArrayList<>();
		daos.add(new TourDao());
		return daos;
	}

	@Override
	protected void fillExtenderFields(SqlQueryDaoFacadeResultItem item, TourSheduleExtender extender)
	{
		extender.setShedule((TourShedule) item.getEntitys().get(0));
		extender.setTour((Tour)item.getEntitys().get(1));
	}

}
