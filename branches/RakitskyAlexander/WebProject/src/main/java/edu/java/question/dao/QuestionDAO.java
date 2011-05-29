/**
 * 
 */
package edu.java.question.dao;

import java.util.List;

import edu.java.testingSystems.domain.Question;

/**
 * object data access layer using Hibernate
 * 
 * @author Rakitsky Alexander
 * @see edu.java.testingSystems.domain.Question
 */
public interface QuestionDAO {
	/**
	 * add a question to the database
	 * 
	 * @param question
	 * @see edu.java.testingSystems.domain.Question
	 */
	public void addQuestion(Question question);

	/**
	 * reads the all questions from the database
	 * 
	 * @return list of questions
	 * @see edu.java.testingSystems.domain.Question
	 */
	public List<Question> listQuestion();

	/**
	 * removes the question from the database
	 * 
	 * @param id
	 *            number question
	 * @see edu.java.testingSystems.domain.Question
	 */
	public void removeQuestion(Integer id);
}
