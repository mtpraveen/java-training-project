/**
 * 
 */
package com.travel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.travel.db.ConnectionManager;
import com.travel.exceptions.DbSqlException;
import com.travel.pojo.BaseEntity;

/**
 * @author dima
 * 
 */
public abstract class BaseDao<T extends BaseEntity>
{
	private static Logger LOGGER = Logger.getLogger(BaseDao.class);

	// abstract classes depends from each entity
	public abstract String getTableName();

	// all columns except column "id"
	public abstract String[] getColumnNames();

	public abstract Class<T> getEntityClass();

	//
	public final T newEntity()
	{
		try
		{
			return getEntityClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			// i think errors here are impossible
			LOGGER.error("Error instanciating class " + getEntityClass().getName(), e);
			return null;
		}
	}

	public String getColumn(int index)
	{
		return getTableName() + "." + getColumnNames()[index];
	}

	public String getIdColumn()
	{
		return getTableName() + ".id";
	}

	public T update(T obj) throws DbSqlException
	{
		EntityManager em = ConnectionManager.getEntityManager();
		obj = em.merge(obj);
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		return obj;
	}

	public T create(T entity)
	{
		EntityManager em = ConnectionManager.getEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}

	public T findById(long l)
	{
		return ConnectionManager.getEntityManager().find(getEntityClass(), l);
	}

	public void delete(long l)
	{
		delete(findById(l));
	}

	public void delete(T obj)
	{
		if (obj != null)
		{
			EntityManager em = ConnectionManager.getEntityManager();
			obj = em.merge(obj);//
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
		}
	}

	public void deleteAll()
	{
		EntityManager em = ConnectionManager.getEntityManager();
		Query query = em.createQuery("DELETE from " + getEntityClass().getSimpleName() + " x");
		em.getTransaction().begin();
		query.executeUpdate();
		em.getTransaction().commit();
	}

	public List<T> findAll()
	{
		return ConnectionManager.getHibernateSession().createCriteria(getEntityClass()).list();
	}
}
