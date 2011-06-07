/**
 * 
 */
package edu.java.answers.service;

import java.util.List;
import edu.java.testingSystems.domain.Answer;

/**
 * object data access layer using Hibernate
 * @author Rakitsky Alexander
 * @see edu.java.testingSystems.domain.Answer
 * @see edu.java.answers.dao.AnswersDAO
 */
public interface AnswersService {
	/**
	 * add a answer to the database
	 * @param answer
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.answers.dao.AnswersDAO#addAnswer(Answer)
	 */
	public void addAnswer(Answer answer);
	/**
	 * removes the answer from the database
	 * @param id
	 *@see edu.java.testingSystems.domain.Answer 
	 *@see edu.java.testingSystems.domain.Answer#id
	 *@see edu.java.answers.dao.AnswersDAO#removeAnswer(Integer)
	 */
	public void removeAnswer(Integer id);
	/**
	 * reads the all answers from the database
	 * 
	 * @return list of all questions
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.answers.dao.AnswersDAO#listAnswers()
	 */
	public List<Answer> listAnswers();
	/**
	 * reads the answers where 'user' = current user from the database
	 * 
	 * @return list of result questions
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.testingSystems.domain.User#getUserNowName()
	 * @see edu.java.answers.dao.AnswersDAO#listAnswerUserNow()
	 */
	public List<Answer> listAnswerUserNow();
	/**
	 * searches in the database by language and level of programming
	 * 
	 * @return list of result question
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.testingSystems.domain.Answer#level
	 * @see edu.java.testingSystems.domain.Answer#language
	 * @see edu.java.answers.dao.AnswersDAO#listFindLanguage(String, String)
	 */
	public List<Answer> listFindLanguage(String language,String level);
}
