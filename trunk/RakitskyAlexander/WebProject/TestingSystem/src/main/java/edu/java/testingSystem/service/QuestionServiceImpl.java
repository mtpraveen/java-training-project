/**
 * 
 */
package edu.java.testingSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.java.testingSystem.dao.QuestionDAO;
import edu.java.testingSystem.domain.Question;
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
	 * @see edu.java.testingSystem.service.QuestionService#addQuestion(edu.java.testingSystem.domain.Question)
	 */
	@Transactional
	public void addQuestion(Question question) {
		questionDAO.addQuestion(question);
	}

	/**
	 * @see edu.java.testingSystem.service.QuestionService#listQustion()
	 */
	@Transactional
	public List<Question> listQustion() {
		return questionDAO.listQustion();
	}

	/**
	 * @see edu.java.testingSystem.service.QuestionService#removeQuestion(java.lang.Integer)
	 */
	@Transactional
	public void removeQuestion(Integer id) {
		questionDAO.removeQuestion(id);
	}

}
