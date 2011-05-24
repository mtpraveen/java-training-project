/**
 * 
 */
package edu.java.answers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.java.answers.dao.AnswersDAO;
import edu.java.testingSystems.domain.Answer;

/**
 * @author Администратор
 *
 */
@Service
public class AnswersServiceImpl implements AnswersService {
	@Autowired
    private AnswersDAO testingDAO;
	/**
	 * @see edu.java.answers.service.AnswersService#addAnswer(edu.java.TestingSystem.domain.Answer)
	 */
	@Transactional
	public void addAnswer(Answer answer) {
		testingDAO.addAnswer(answer);
	}

	/**
	 * @see edu.java.answers.service.AnswersService#removeAnswer(java.lang.Integer)
	 */
	@Transactional
	public void removeAnswer(Integer id) {
		testingDAO.removeAnswer(id);
	}

	/**
	 * @see edu.java.answers.service.AnswersService#listAnswers()
	 */
	@Transactional
	public List<Answer> listAnswers() {
		return testingDAO.listAnswers();
	}
	@Transactional
	public List<Answer> listAnswerUserNow()
	{
		return testingDAO.listAnswerUserNow();
	}
	@Transactional
	public List<Answer> listFindLanguage(String language,String level)
	{
		return testingDAO.listFindLanguage(language, level);
	}
}
