package epam.com.votemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.com.votemanager.dao.ContactDAO;
import epam.com.votemanager.dao.QuestionDAO;
import epam.com.votemanager.domain.Contact;
import epam.com.votemanager.domain.Question;

@Service
public class ContactService implements IContactService {

	@Autowired
	private ContactDAO contactDAO;

	@Autowired
	private QuestionDAO questionDAO;

	@Transactional
	public void addContact(Contact contact) {
		contactDAO.addContact(contact);
	}

	@Transactional
	public List<Contact> listContact() {

		return contactDAO.listContact();
	}

	@Transactional
	public void removeContact(Integer id) {
		contactDAO.removeContact(id);
	}

	@Transactional
	public List<Question> questionForCurrentUser() {
		List<Question> answered = contactDAO.answeredQuestions();
		List<Question> all = questionDAO.listQuestions();
		if (!answered.isEmpty()) {
			for (Question question : answered) {
				if (all.contains(question)) {
					all.remove(question);
				}
			}
		}
		if (!all.isEmpty()) {
			int size = all.size();
			for (int i = 0; i < size; i++) {
				if (all.get(i).getQuestionVarinats().isEmpty()) {
					all.remove(i);
					i--;
					size--;
				}
			}
		}
		return all;
	}

	@Transactional
	public void vote(Integer variantId) {
		contactDAO.vote(variantId);
	}

	@Transactional
	public boolean isUniqueEmail(Contact contact) {
		return contactDAO.isUniqueEmail(contact);
	}
}
