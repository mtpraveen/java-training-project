/**
 * 
 */
package edu.java.answers.dao;

import java.util.List;
import edu.java.testingSystems.domain.Answer;



/**
 * @author �������������
 *
 */
public interface AnswersDAO {
	public void addAnswer(Answer answer);
	public void removeAnswer(Integer id);
	public List<Answer> listAnswers();
	public List<Answer> listAnswerUserNow();
	public List<Answer> listFindLanguage(String language,String level);
}
