package edu.java.auction.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.java.auction.domain.Tovar;
import edu.java.auction.domain.User;


@Repository
public class TovarDAOImpl implements TovarDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addTovar(Tovar tovar) {
		sessionFactory.getCurrentSession().save(tovar);
	}

	@SuppressWarnings("unchecked")
	public List<Tovar> listTovar() {

		return sessionFactory.getCurrentSession().createQuery("from Tovar")
			.list();
	}

	public void removeTovar(Integer id) {
		Tovar tovar = (Tovar) sessionFactory.getCurrentSession().load(
				Tovar.class, id);
		if (null != tovar) {
			sessionFactory.getCurrentSession().delete(tovar);
		}

	}
	
	
	public void payTovar(Integer id) {
		
		Tovar tovar = (Tovar) sessionFactory.getCurrentSession().load(
				Tovar.class, id);
		if (tovar.getUser()!=User.getUserNowName()){
		//tovar.setPrice(tovar.getPrice()+5);
		 //sessionFactory.getCurrentSession().update(tovar);
		sessionFactory.getCurrentSession().createQuery("UPDATE Tovar SET price=price+5 where id='"+id+"'").executeUpdate();
		sessionFactory.getCurrentSession().createQuery("UPDATE Tovar SET user='"+User.getUserNowName()+"'where id='"+id+"'").executeUpdate();
		}else System.out.println("rfgfg!");
		}
	

	
	
	@SuppressWarnings("unchecked")
	public List<Tovar> listFindTovar(String name)
	{
		return sessionFactory.getCurrentSession().createQuery("from Tovar where name = '" + name+"'").list();
	}
	
	
	//getHibernateTemplate().update(t);
	
	
	
	
}
