package voting.web;

import static voting.security.VotingUserDetailsService.getCurrentAccount;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import voting.domain.Account;
import voting.domain.Answer;
import voting.domain.Category;
import voting.domain.Question;
import voting.domain.SystemRole;
import voting.domain.Vote;

@Controller
public class QuestionController {

	private static final Logger LOGGER = Logger.getLogger(CategoryController.class);

	@RequestMapping(value = "/questions", method = RequestMethod.POST)
	public String create(@Valid Question question, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to create new question");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("question", question);
			LOGGER.warn(getCurrentAccount(httpServletRequest) + ": wrong question data");
			return "questions/create";
		}
		uiModel.asMap().clear();
		question.persist();
		LOGGER.info("new category " + question.getName() + " was successfully created");
		return "redirect:/questions/addanswers/" + question.getId();
	}

	@RequestMapping(value = "/questions", params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		uiModel.addAttribute("question", new Question());
		return "questions/create";
	}

	@RequestMapping(value = "questions/addanswers/{id}", method = RequestMethod.GET)
	public String addAnswers(@PathVariable("id") Long id, Model uiModel) {
		Question question = Question.findQuestion(id);
		uiModel.addAttribute("question", question);
		uiModel.addAttribute("answers", question.getAnswers());
		uiModel.addAttribute("answer", new Answer());
		uiModel.addAttribute("itemId", id);
		return "questions/addanswers";
	}

	@RequestMapping(value = "/results/{id}", method = RequestMethod.GET)
	public String showResults(@PathVariable("id") Long id, Model uiModel,
			HttpServletRequest httpServletRequest) {
		Question question = Question.findQuestion(id);
		Account account = getCurrentAccount(httpServletRequest);
		// hacking attempt(user tries to watch result without voting)?
		if ((account == null || Vote
				.countQuestionVotesByUser(question, account) == 0)
				&& account.getSystemRole() != SystemRole.ADMIN) {
			LOGGER.error(account.getName() + " tries to watch results of the question ¹ " + id + " without voting");
			return "redirect:/category/" + question.getCategory().getId()
					+ "?page=1&size=5";
		}
		HashMap<Answer, Long> pieData = new HashMap<Answer, Long>();
		Calendar calendar = new GregorianCalendar();
		Date endDate = calendar.getTime();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);

		Date startDate = question.getCreationDate();
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		calendar.setTime(startDate);

		for (Answer answer : question.getAnswers()) {
			pieData.put(answer,
					Vote.countAnswersBetweenDates(answer, startDate, endDate));
		}

		ArrayList<Date> dates = new ArrayList<Date>();
		while (calendar.getTime().before(endDate)) {
			dates.add(calendar.getTime());
			calendar.add(Calendar.DATE, 1);
		}

		LinkedHashMap<Answer, ArrayList<Long>> chartData = new LinkedHashMap<Answer, ArrayList<Long>>();
		for (Answer answer : question.getAnswers()) {
			ArrayList<Long> answerValues = new ArrayList<Long>();
			for (Date date : dates) {
				// set date to the start of the day
				Date dayStart = (Date) date.clone();
				dayStart.setSeconds(0);
				dayStart.setMinutes(0);
				dayStart.setHours(0);
				// set date to the end of the day
				Date dayEnd = (Date) date.clone();
				dayEnd.setSeconds(59);
				dayEnd.setMinutes(59);
				dayEnd.setHours(23);
				answerValues.add(Vote.countAnswersBetweenDates(answer,
						dayStart, dayEnd));
			}
			chartData.put(answer, answerValues);
		}

		for (Answer answer : question.getAnswers()) {
			String text = answer.getText();

			if (text.length() > 50) {
				answer.setText(text.substring(0, 50) + "...");
			}
		}

		String questName = question.getName();
		if (questName.length() > 35)
			question.setName(questName.substring(0, 35) + "...");

		uiModel.addAttribute("question", question);
		uiModel.addAttribute("pieData", pieData);
		uiModel.addAttribute("chartData", chartData);
		uiModel.addAttribute("chartLables", dates);
		return "questions/results";
	}

	@RequestMapping(value = "/vote/{id}", method = RequestMethod.GET)
	public String voteForm(@PathVariable("id") Long id, Model uiModel,
			HttpServletRequest httpServletRequest) {
		Question question = Question.findQuestion(id);
		Account account = getCurrentAccount(httpServletRequest);
		// hacking attempt(user tries to vote second time)?
		if ((account == null || Vote
				.countQuestionVotesByUser(question, account) != 0)
				&& account.getSystemRole() != SystemRole.ADMIN) {
			LOGGER.error(account.getName() + " tries to vote second time on question ¹ " + id);
			return "redirect:/category/" + question.getCategory().getId()
					+ "?page=1&size=5";
		}
		uiModel.addAttribute("question", question);
		uiModel.addAttribute("vote", new Vote());
		return "questions/vote";
	}

	@RequestMapping(value = "/vote/{id}", method = RequestMethod.POST)
	public String acceptVote(@PathVariable("id") Long id,
			@ModelAttribute("vote") Vote vote,
			HttpServletRequest httpServletRequest) {
		Account account = getCurrentAccount(httpServletRequest);
		LOGGER.info(account.getName() + " tries to vote on question ¹ " + id);
		vote.setAccount(account);
		vote.setQuestion(Question.findQuestion(id));
		vote.persist();
		LOGGER.info(account.getName() + "'s vote was successfully accepted");
		return "redirect:/results/" + id;
	}

	@RequestMapping(value = "/questions/answers", method = RequestMethod.POST)
	public String createAnswer(
			@RequestParam(value = "question_id", required = true) Long questionId,
			@Valid Answer answer, Model uiModel, HttpServletRequest httpServletRequest) {
		LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to create new answer for question ¹ " + questionId + "...");
		uiModel.asMap().clear();
		answer.setQuestion(Question.findQuestion(questionId));
		answer.persist();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": answer ¹ " + answer.getId() + " was sucessfully added ");
		return "redirect:/questions/addanswers/" + questionId;
	}

	@RequestMapping(value = "/questions/answers/{id}", method = RequestMethod.DELETE)
	public String deleteAnswer(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
		Answer answer = Answer.findAnswer(id);
		Question question = answer.getQuestion();
		LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to remove answer ¹ " + id + " from question ¹ " + question.getId() +"...");
		answer.remove();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ":  answer ¹ " + id + " was successfully removed");
		uiModel.asMap().clear();
		return "redirect:/questions/addanswers/" + question.getId();
	}

	@RequestMapping(value = "/questions", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			uiModel.addAttribute("questions", Question.findQuestionEntries(
					page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
			float nrOfPages = (float) Question.countQuestions() / sizeNo;
			uiModel.addAttribute(
					"maxPages",
					(int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1
							: nrOfPages));
		} else {
			uiModel.addAttribute("questions", Question.findAllQuestions());
		}
		return "questions/list";
	}

	@RequestMapping(value = "/questions", method = RequestMethod.PUT)
	public String update(@Valid Question question, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest) {
		LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to update question ¹ " + question.getId() + "...");
		if (bindingResult.hasErrors()) {
			uiModel.addAttribute("question", question);
			LOGGER.warn(getCurrentAccount(httpServletRequest) + ": wrong update data for question ¹ "+ question.getId());
			return "questions/update";
		}
		uiModel.asMap().clear();
		question.merge();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": question ¹ "+ question.getId() + "was successfully updated");
		return "redirect:/questions/addanswers/" + question.getId();
	}

	@RequestMapping(value = "/questions/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("question", Question.findQuestion(id));
		return "questions/update";
	}

	@RequestMapping(value = "/questions/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("id") Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			Model uiModel, HttpServletRequest httpServletRequest) {
		Question question = Question.findQuestion(id);
		LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to delete question ¹ " + question.getId() + "...");
		question.remove();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": question ¹ " + question.getId() + " was successfully removed");
		uiModel.asMap().clear();
		uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
		uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
		return "redirect:/questions";
	}

	@ModelAttribute("accounts")
	public Collection<Account> populateAccounts() {
		return Account.findAllAccounts();
	}

	@ModelAttribute("categories")
	public java.util.Collection<Category> populatecategories() {
		return Category.findAllCategories();
	}

	@ModelAttribute("questions")
	public java.util.Collection<Question> populateQuestions() {
		return Question.findAllQuestions();
	}

	@ModelAttribute("votes")
	public java.util.Collection<Vote> populateVotes() {
		return Vote.findAllVotes();
	}

	@ModelAttribute("date_format")
	String addDateTimeFormatPatterns() {
		return DateTimeFormat.patternForStyle("S-",
				LocaleContextHolder.getLocale());
	}

	@ModelAttribute("categories_menu")
	public java.util.Map<Category, Long> populateCategories() {
		LinkedHashMap<Category, Long> categories = new LinkedHashMap<Category, Long>();
		for (Category category : Category.findAllCategories()) {
			categories.put(category, Category.countQuestions(category));
		}
		return categories;
	}
}
