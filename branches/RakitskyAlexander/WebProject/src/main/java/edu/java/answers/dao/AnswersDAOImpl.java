/**
 * 
 */
package edu.java.answers.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.java.testingSystems.domain.Answer;
import edu.java.testingSystems.domain.User;


/**
 * @author Администратор
 * 
 */
@Repository
public class AnswersDAOImpl implements AnswersDAO {
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * @see edu.java.answers.dao.AnswersDAO#addAnswer(edu.java.TestingSystem.domain.Answer)
	 */
	public void addAnswer(Answer answer) {
		sessionFactory.getCurrentSession().save(answer);
	}

	/**
	 * @see edu.java.answers.dao.AnswersDAO#removeAnswer(java.lang.Integer)
	 */
	public void removeAnswer(Integer id) {
		Answer answer = (Answer) sessionFactory.getCurrentSession().load(
				Answer.class, id);
		if (answer != null) {
			sessionFactory.getCurrentSession().delete(answer);
		}
	}

	/**
	 * @see edu.java.answers.dao.AnswersDAO#listAnswers()
	 */
	@SuppressWarnings("unchecked")
	public List<Answer> listAnswers() {
		return sessionFactory.getCurrentSession().createQuery("from Answer").list();
	}
	@SuppressWarnings("unchecked")
	public List<Answer> listAnswerUserNow()
	{
		return sessionFactory.getCurrentSession().createQuery("from Answer where user = '"+User.getUserNowName()+"'").list();
	}
	@SuppressWarnings("unchecked")
	public List<Answer> listFindLanguage(String language,String level)
	{
		return sessionFactory.getCurrentSession().createQuery("from Answer where language = '" + language+"'"+" AND level = '"+level+"'").list();
	}
}
