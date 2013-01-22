package epam.com.votemanager.service;

import java.sql.Date;
import java.util.List;

import epam.com.votemanager.domain.Question;
import epam.com.votemanager.domain.Variant;

public interface IQuestionService {

	public void addQuestion(Question question);

	public List<Question> listQuestion();

	public void removeQuestion(Integer id);

	public List<Variant> variants(Integer id);

	public void updateQuestion(Question question);

	public Question getQuestion(Integer id);

	public void addVariant(Integer questionId, Variant variant);

	public List<Question> listResults();

	public List<Question> todayResult(Date date);
}