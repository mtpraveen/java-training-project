/**
 * 
 */
package edu.java.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.java.question.dao.QuestionDAO;
import edu.java.testingSystems.domain.Question;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rakitsky Alexander
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
    private QuestionDAO questionDAO;
	/**
	 * @see edu.java.question.service.QuestionService#addQuestion(edu.java.TestingSystem.domain.Question)
	 */
	@Transactional
	public void addQuestion(Question question) {
		questionDAO.addQuestion(question);
	}

	/**
	 * @see edu.java.question.service.QuestionService#listQustion()
	 */
	@Transactional
	public List<Question> listQuestion() {
		return questionDAO.listQuestion();
	}

	/**
	 * @see edu.java.question.service.QuestionService#removeQuestion(java.lang.Integer)
	 */
	@Transactional
	public void removeQuestion(Integer id) {
		questionDAO.removeQuestion(id);
	}

}
