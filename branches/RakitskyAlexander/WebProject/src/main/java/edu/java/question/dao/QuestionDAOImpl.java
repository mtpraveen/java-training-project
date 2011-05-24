package edu.java.question.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import edu.java.testingSystems.domain.Question;
/**
 * @author Rakitsky Alexander
 *
 */
@Repository
public class QuestionDAOImpl implements QuestionDAO {
	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * @see edu.java.question.dao.QuestionDAO#addQuestion(edu.java.TestingSystem.domain.Question)
	 */
	public void addQuestion(Question question) {
		sessionFactory.getCurrentSession().save(question);
	}

	/**
	 * @see edu.java.question.dao.QuestionDAO#listQustion()
	 */
	@SuppressWarnings("unchecked")
	public List<Question> listQuestion() {
		return sessionFactory.getCurrentSession().createQuery("from Question").list();
	}

	/**
	 * @see edu.java.question.dao.QuestionDAO#removeQuestion(java.lang.Integer)
	 */
	public void removeQuestion(Integer id) {
		Question question= (Question) sessionFactory.getCurrentSession().load(Question.class	, id);
		if (question!=null)
		{
			sessionFactory.getCurrentSession().delete(question);
		}
	}

}
