package edu.java.testingSystem.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import edu.java.testingSystem.domain.Question;
import org.hibernate.SessionFactory;
/**
 * @author Rakitsky Alexander
 *
 */
@Repository
public class QuestionDAOImpl implements QuestionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * @see edu.java.testingSystem.dao.QuestionDAO#addQuestion(edu.java.testingSystem.domain.Question)
	 */
	public void addQuestion(Question question) {
		sessionFactory.getCurrentSession().save(question);
	}

	/**
	 * @see edu.java.testingSystem.dao.QuestionDAO#listQustion()
	 */
	@SuppressWarnings("unchecked")
	public List<Question> listQustion() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
	}

	/**
	 * @see edu.java.testingSystem.dao.QuestionDAO#removeQuestion(java.lang.Integer)
	 */
	public void removeQuestion(Integer id) {
		Question question= (Question) sessionFactory.getCurrentSession().load(Question.class	, id);
		if (question!=null)
		{
			sessionFactory.getCurrentSession().delete(question);
		}
	}

}
