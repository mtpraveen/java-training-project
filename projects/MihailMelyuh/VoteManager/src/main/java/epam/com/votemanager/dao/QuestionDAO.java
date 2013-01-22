package epam.com.votemanager.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.com.votemanager.domain.Question;
import epam.com.votemanager.domain.Variant;
import epam.com.votemanager.domain.Vote;

@Repository
public class QuestionDAO implements IQuestions {

	@Autowired
	private SessionFactory sessionFactory;

	public void addQuestion(Question question) {
		sessionFactory.getCurrentSession().save(question);

	}

	@SuppressWarnings("unchecked")
	public List<Question> listQuestions() {
		return sessionFactory.getCurrentSession().createQuery("from Question")
				.list();
	}

	@Override
	public void removeQuestion(Integer id) {
		Question question = (Question) sessionFactory.getCurrentSession().load(
				Question.class, id);
		if (null != question) {
			sessionFactory.getCurrentSession().delete(question);
		}
	}

	@Override
	public List<Variant> variants(Integer id) {
		return ((Question) sessionFactory.getCurrentSession().load(
				Question.class, id)).getQuestionVarinats();
	}

	@Override
	public void updateQuestion(Question question) {
		sessionFactory.getCurrentSession().saveOrUpdate(question);
	}

	@Override
	public Question getQuestion(Integer id) {
		return (Question) sessionFactory.getCurrentSession().load(
				Question.class, id);
	}

	@Override
	public void addVariant(Integer questionId, Variant variant) {
		Session currentSession = sessionFactory.getCurrentSession();
		Question question = (Question) currentSession.load(Question.class,
				questionId);
		variant.setQuestion(question);
		question.getQuestionVarinats().add(variant);
		currentSession.saveOrUpdate(question);
	}

	@Override
	public List<Question> listResults() {
		@SuppressWarnings("unchecked")
		List<Question> questions = sessionFactory.getCurrentSession()
				.createQuery("from Question").list();
		for (Question question : questions) {
			for (Variant variant : question.getQuestionVarinats()) {
				Query query = sessionFactory.getCurrentSession().createQuery(
						"select count (*) from " + Vote.class.getName()
								+ " where idVariant = :id ");
				query.setParameter("id", variant.getId());
				variant.setCount((Long) query.uniqueResult());
			}
		}
		return questions;
	}

	@SuppressWarnings("deprecation")
	public List<Question> todayResults(Date date) {
		Date nextDate = new Date(date.getTime());
		nextDate.setDate(date.getDate() + 1);
		@SuppressWarnings("unchecked")
		List<Question> questions = sessionFactory.getCurrentSession()
				.createQuery("from Question").list();
		for (Question question : questions) {
			for (Variant variant : question.getQuestionVarinats()) {
				Query query = sessionFactory
						.getCurrentSession()
						.createQuery(
								"select count (*) from "
										+ Vote.class.getName()
										+ " where idVariant = :id and Date between :dateStart and :dateEnd");
				query.setParameter("id", variant.getId());
				query.setParameter("dateStart", date);
				query.setParameter("dateEnd", nextDate);
				variant.setCount((Long) query.uniqueResult());
			}
		}
		return questions;
	}

}