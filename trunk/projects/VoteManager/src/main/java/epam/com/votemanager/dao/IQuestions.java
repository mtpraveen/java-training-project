package epam.com.votemanager.dao;

import java.sql.Date;
import java.util.List;

import epam.com.votemanager.domain.Question;
import epam.com.votemanager.domain.Variant;

public interface IQuestions {

	public void addQuestion(Question question);

	public List<Question> listQuestions();

	public void removeQuestion(Integer id);

	public List<Variant> variants(Integer id);

	public void updateQuestion(Question question);

	public Question getQuestion(Integer id);

	public void addVariant(Integer questionId, Variant variant);

	public List<Question> listResults();

	public List<Question> todayResults(Date date);

}
