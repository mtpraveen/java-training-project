/**
 * 
 */
package edu.java.question.service;

import java.util.List;
import edu.java.testingSystems.domain.Question;


/**
 * @author Rakitsky Alexander
 *
 */
public interface QuestionService {
	/**
	 * add a question to the database
	 * @param question
	 */
	public void addQuestion(Question question);
	/**
	 * reads the questions from the database
	 * @return list of questions
	 */
	public List<Question> listQuestion();
	/**
	 * removes the question from the database
	 * @param id number question
	 */
	public void removeQuestion(Integer id);
}
