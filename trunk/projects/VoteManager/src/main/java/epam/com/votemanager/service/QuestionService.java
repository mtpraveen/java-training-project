package epam.com.votemanager.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.com.votemanager.dao.QuestionDAO;
import epam.com.votemanager.domain.Question;
import epam.com.votemanager.domain.Variant;

@Service
public class QuestionService implements IQuestionService {

	@Autowired
	private QuestionDAO questionDAO;

	@Transactional
	public void addQuestion(Question question) {
		questionDAO.addQuestion(question);
	}

	@Transactional
	public List<Question> listQuestion() {

		return questionDAO.listQuestions();
	}

	@Transactional
	public void removeQuestion(Integer id) {
		questionDAO.removeQuestion(id);
	}

	@Transactional
	public List<Variant> variants(Integer id) {
		return questionDAO.variants(id);
	}

	@Transactional
	public void updateQuestion(Question question) {
		questionDAO.updateQuestion(question);
	}

	@Transactional
	public Question getQuestion(Integer id) {
		return questionDAO.getQuestion(id);
	}

	@Transactional
	public void addVariant(Integer questionId, Variant variant) {
		questionDAO.addVariant(questionId, variant);
	}

	@Transactional
	public List<Question> listResults() {
		return questionDAO.listResults();
	}

	@Transactional
	public List<Question> todayResult(Date date) {
		return questionDAO.todayResults(date);
	}
}