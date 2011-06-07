/**
 * 
 */
package edu.java.question.service;

import java.util.List;
import edu.java.testingSystems.domain.Question;


/**
 * layer of user interaction 
 * 
 * @author Rakitsky Alexander
 * @see edu.java.testingSystems.domain.Question
 * @see edu.java.question.dao.QuestionDAO
 */
public interface QuestionService {
	/**
	 * add a question to the database
	 * 
	 * @param question
	 * @see edu.java.question.dao.QuestionDAO#addQuestion(Question)
	 */
	public void addQuestion(Question question);
	/**
	 * reads the all questions from the database
	 * 
	 * @return list of questions
	 * @see edu.java.question.dao.QuestionDAO#listQuestion()
	 */
	public List<Question> listQuestion();
	/**
	 * removes the question from the database
	 * 
	 * @param id
	 *            number question
	 * @see edu.java.question.dao.QuestionDAO#removeQuestion(Integer)
	 */
	public void removeQuestion(Integer id);
}
