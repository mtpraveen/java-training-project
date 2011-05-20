/**
 * 
 */
package edu.java.testing.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.java.testingSystem.domain.Testing;
import edu.java.testingSystem.domain.User;

/**
 * @author Администратор
 * 
 */
@Repository
public class TestingDAOImpl implements TestingDAO {
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * @see edu.java.testing.dao.TestingDAO#addTesting(edu.java.testingSystem.domain.Testing)
	 */
	public void addTesting(Testing tesitng) {
		sessionFactory.getCurrentSession().save(tesitng);

	}

	/**
	 * @see edu.java.testing.dao.TestingDAO#removeTesting(java.lang.Integer)
	 */
	public void removeTesting(Integer id) {
		Testing testing = (Testing) sessionFactory.getCurrentSession().load(
				Testing.class, id);
		if (testing != null) {
			sessionFactory.getCurrentSession().delete(testing);
		}
	}

	/**
	 * @see edu.java.testing.dao.TestingDAO#listTesting()
	 */
	@SuppressWarnings("unchecked")
	public List<Testing> listTesting() {
		return sessionFactory.getCurrentSession().createQuery("from Testing").list();
	}
	@SuppressWarnings("unchecked")
	public List<Testing> listTestingForUser()
	{
		User user = new User();
		return sessionFactory.getCurrentSession().createQuery("from Testing where user = '"+user.getUserNowName()+"'").list();
	}
	
}
