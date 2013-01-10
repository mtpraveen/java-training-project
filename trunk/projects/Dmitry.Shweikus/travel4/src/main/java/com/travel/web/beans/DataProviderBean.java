/**
 * 
 */
package com.travel.web.beans;

import java.util.ArrayList;
import java.util.List;

import com.travel.dao.BaseDao;
import com.travel.dao.utils.ManyToOneConditionConstrainBuilder;
import com.travel.dao.utils.SqlQueryDaoFacade;
import com.travel.dao.utils.SqlQueryDaoFacade.SqlQueryDaoFacadeResultItem;
import com.travel.db.ApplicationException;
import com.travel.db.ConnectionManager;
import com.travel.pojo.BaseEntity;

/**
 * @author dima
 *
 */
public class DataProviderBean
{
	private BaseDao dao;
	private BaseDao dao2;
	private BaseDao dao3;
	private BaseDao dao4;
	private BaseDao dao5;
	private BaseDao dao6;
	private BaseDao dao7;
	private BaseDao dao8;
	private BaseDao dao9;
	private BaseDao masterDao;
	private long entityId;
	private long masterId;
	private String customConstrains = "";
	
	private List<BaseDao> getDaos()
	{
		List<BaseDao> res = new ArrayList<>();
		res.add(dao);
		if (dao2 == null) return res; else res.add(dao2);
		if (dao3 == null) return res; else res.add(dao3);
		if (dao4 == null) return res; else res.add(dao4);
		if (dao5 == null) return res; else res.add(dao5);
		if (dao6 == null) return res; else res.add(dao6);
		if (dao7 == null) return res; else res.add(dao7);
		if (dao8 == null) return res; else res.add(dao8);
		if (dao9 == null) return res; else res.add(dao9);
		return res;
	}
	
	public BaseEntity getRecordById() throws ApplicationException
	{
		return dao.findById(entityId);
	}
	
	public List<SqlQueryDaoFacadeResultItem> getRecords() throws ApplicationException
	{
		SqlQueryDaoFacade facade = new SqlQueryDaoFacade(ConnectionManager.getInstance().getConnection());
		String constrains = "";
		if (!getCustomConstrains().equals(""))
			constrains += " WHERE " + getCustomConstrains();
		return facade.executeQuery(getDaos(), constrains, null);
	}
	public List getSimpleRecords() throws ApplicationException
	{
		List<BaseEntity> entities = new ArrayList<>();
		for (SqlQueryDaoFacadeResultItem item : getRecords())
		{
			entities.add(item.getEntitys().get(0));
		}
		return entities;
	}

	public List getDetailRecords() throws ApplicationException
	{
		SqlQueryDaoFacade facade = new SqlQueryDaoFacade(ConnectionManager.getInstance().getConnection());
		ManyToOneConditionConstrainBuilder builder = new ManyToOneConditionConstrainBuilder();
		builder.addConstrainWithId(masterDao, dao, masterId);
		String constrains = builder.build();
		if (!getCustomConstrains().equals(""))
			constrains += " AND " + getCustomConstrains();
		return facade.executeQuery(getDaos(), "WHERE " + constrains, null);
	}
	/**
	 * @return the dao
	 */
	public BaseDao getDao()
	{
		return dao;
	}
	/**
	 * @param dao the dao to set
	 */
	public void setDao(BaseDao dao)
	{
		this.dao = dao;
	}
	/**
	 * @return the dao2
	 */
	public BaseDao getDao2()
	{
		return dao2;
	}
	/**
	 * @param dao2 the dao2 to set
	 */
	public void setDao2(BaseDao dao2)
	{
		this.dao2 = dao2;
	}
	/**
	 * @return the dao3
	 */
	public BaseDao getDao3()
	{
		return dao3;
	}
	/**
	 * @param dao3 the dao3 to set
	 */
	public void setDao3(BaseDao dao3)
	{
		this.dao3 = dao3;
	}
	/**
	 * @return the dao4
	 */
	public BaseDao getDao4()
	{
		return dao4;
	}
	/**
	 * @param dao4 the dao4 to set
	 */
	public void setDao4(BaseDao dao4)
	{
		this.dao4 = dao4;
	}
	/**
	 * @return the dao5
	 */
	public BaseDao getDao5()
	{
		return dao5;
	}
	/**
	 * @param dao5 the dao5 to set
	 */
	public void setDao5(BaseDao dao5)
	{
		this.dao5 = dao5;
	}
	/**
	 * @return the dao6
	 */
	public BaseDao getDao6()
	{
		return dao6;
	}
	/**
	 * @param dao6 the dao6 to set
	 */
	public void setDao6(BaseDao dao6)
	{
		this.dao6 = dao6;
	}
	/**
	 * @return the dao7
	 */
	public BaseDao getDao7()
	{
		return dao7;
	}
	/**
	 * @param dao7 the dao7 to set
	 */
	public void setDao7(BaseDao dao7)
	{
		this.dao7 = dao7;
	}
	/**
	 * @return the dao8
	 */
	public BaseDao getDao8()
	{
		return dao8;
	}
	/**
	 * @param dao8 the dao8 to set
	 */
	public void setDao8(BaseDao dao8)
	{
		this.dao8 = dao8;
	}
	/**
	 * @return the dao9
	 */
	public BaseDao getDao9()
	{
		return dao9;
	}
	/**
	 * @param dao9 the dao9 to set
	 */
	public void setDao9(BaseDao dao9)
	{
		this.dao9 = dao9;
	}
	/**
	 * @return the masterDao
	 */
	public BaseDao getMasterDao()
	{
		return masterDao;
	}
	/**
	 * @param masterDao the masterDao to set
	 */
	public void setMasterDao(BaseDao masterDao)
	{
		this.masterDao = masterDao;
	}
	/**
	 * @return the entityId
	 */
	public long getEntityId()
	{
		return entityId;
	}
	/**
	 * @param entityId the entityId to set
	 */
	public void setEntityId(long entityId)
	{
		this.entityId = entityId;
	}
	/**
	 * @return the masterId
	 */
	public long getMasterId()
	{
		return masterId;
	}
	/**
	 * @param masterId the masterId to set
	 */
	public void setMasterId(long masterId)
	{
		this.masterId = masterId;
	}

	/**
	 * @return the customConstrains
	 */
	public String getCustomConstrains()
	{
		return customConstrains;
	}

	/**
	 * @param customConstrains the customConstrains to set
	 */
	public void setCustomConstrains(String customConstrains)
	{
		this.customConstrains = customConstrains;
	}
}
