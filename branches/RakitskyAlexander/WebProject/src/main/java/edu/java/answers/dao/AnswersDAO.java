/**
 * 
 */
package edu.java.answers.dao;

import java.util.List;
import edu.java.testingSystems.domain.Answer;

/**
 * object data access layer using Hibernate
 * 
 * @author Администратор
 * @see edu.java.testingSystems.domain.Question
 */
public interface AnswersDAO {
	/**
	 * add a answer to the database
	 * 
	 * @param answer
	 * @see edu.java.testingSystems.domain.Answer
	 */
	public void addAnswer(Answer answer);

	/**
	 * removes the answer from the database
	 * 
	 * @param id
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.testingSystems.domain.Answer#id
	 */
	public void removeAnswer(Integer id);

	/**
	 * reads the all answers from the database
	 * 
	 * @return list of all questions
	 * @see edu.java.testingSystems.domain.Answer
	 */
	public List<Answer> listAnswers();

	/**
	 * reads the answers where 'user' = current user from the database
	 * 
	 * @return list of result questions
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.testingSystems.domain.User#getUserNowName()
	 */
	public List<Answer> listAnswerUserNow();

	/**
	 * searches in the database by language and level of programming
	 * 
	 * @return list of result question
	 * @see edu.java.testingSystems.domain.Answer
	 * @see edu.java.testingSystems.domain.Answer#level
	 * @see edu.java.testingSystems.domain.Answer#language
	 */
	public List<Answer> listFindLanguage(String language, String level);
}
