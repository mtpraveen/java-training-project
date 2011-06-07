package edu.java.testingSystem;


import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import edu.java.answers.dao.AnswersDAO;
import edu.java.question.dao.QuestionDAO;
import edu.java.testingSystems.domain.Answer;
import edu.java.testingSystems.domain.Question;

@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class TestDatabase extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private AnswersDAO answersDAO;
	@Transactional
	@Test
	public void addQuestion()
	{
		int sizeOld,sizeNew;
		List<Question> listQuestion = questionDAO.listQuestion();
		sizeOld=listQuestion.size();
		Question question = generetedQuestion();
		questionDAO.addQuestion(question);
		listQuestion = questionDAO.listQuestion();
		sizeNew=listQuestion.size();
		assertEquals(sizeOld,sizeNew-1);
		String language=question.getLanguage();
		listQuestion = questionDAO.listQuestion();
		for(Question questionL:listQuestion)
			if(questionL.getLanguage()==language)
			{
				questionDAO.removeQuestion(questionL.getId());
				System.out.println("DELETE QEUSTION");
			}
	}
	@Transactional
	@Test
	public void addAndDeleteQuestion()
	{
		int sizeOld,sizeNew;
		List<Question> listQuestion = questionDAO.listQuestion();
		sizeOld=listQuestion.size();
		Question question = generetedQuestion();
		questionDAO.addQuestion(question);
		listQuestion = questionDAO.listQuestion();
		sizeNew=listQuestion.size();
		assertEquals(sizeOld,sizeNew-1);
		boolean find=false;
		String language=question.getLanguage();
		listQuestion = questionDAO.listQuestion();
		for(Question questionL:listQuestion)
			if(questionL.getLanguage()==language)
			{
				find=true;
				questionDAO.removeQuestion(questionL.getId());
				System.out.println("DELETE QEUSTION");
			}
		assertTrue(find);
		listQuestion = questionDAO.listQuestion();
		sizeNew=listQuestion.size();
		assertEquals(sizeNew,sizeOld);
	}
	@Transactional
	@Test
	public void addAnswer()
	{
		int sizeOld,sizeNew;
		List<Answer> listAnswers = answersDAO.listAnswers();
		sizeOld=listAnswers.size();
		Answer answer=generetedAnswer();
		answersDAO.addAnswer(answer);
		listAnswers=answersDAO.listAnswers();
		sizeNew=listAnswers.size();
		assertEquals(sizeOld,sizeNew-1);
		String language = answer.getLanguage();
		String level = answer.getLevel();
		for (Answer answerFind:listAnswers)
			if (language==answerFind.getLanguage() && level==answerFind.getLevel())
			{
				answersDAO.removeAnswer(answerFind.getId());
			}
	}
	@Transactional
	@Test
	public void addAndDeleteAnswer()
	{
		int sizeOld,sizeNew;
		List<Answer> listAnswers = answersDAO.listAnswers();
		sizeOld=listAnswers.size();
		Answer answer=generetedAnswer();
		answersDAO.addAnswer(answer);
		listAnswers=answersDAO.listAnswers();
		sizeNew=listAnswers.size();
		assertEquals(sizeOld,sizeNew-1);
		boolean find=false;
		String language = answer.getLanguage();
		String level = answer.getLevel();
		listAnswers=answersDAO.listAnswers();
		for (Answer answerFind:listAnswers)
			if (language==answerFind.getLanguage() && level==answerFind.getLevel())
			{
				find=true;
				answersDAO.removeAnswer(answerFind.getId());
				System.out.println("Delete answer!");
			}
		assertTrue(find);
		sizeNew=answersDAO.listAnswers().size();
		assertEquals(sizeOld, sizeNew);
	}
	@Transactional
	@Test
	public void findAnswer()
	{
		int sizeOld,sizeNew;
		List<Answer> listAnswers = answersDAO.listAnswers();
		sizeOld=listAnswers.size();
		Answer answer=generetedAnswer();
		answersDAO.addAnswer(answer);
		listAnswers=answersDAO.listAnswers();
		sizeNew=listAnswers.size();
		assertEquals(sizeOld,sizeNew-1);
		List<Answer> listFindLanguage = answersDAO.listFindLanguage(answer.getLanguage(), answer.getLevel());
		assertNotNull(listFindLanguage);
	}
	private Question generetedQuestion() {
		Question question = new Question();
		String language=UUID.randomUUID().toString();
		question.setLanguage(language);
		return question;
	}
	private Answer generetedAnswer() {
		Answer answer= new Answer();
		String language=UUID.randomUUID().toString();
		String level="Novice";
		answer.setLanguage(language);
		answer.setLevel(level);
		return answer;
	}
}
