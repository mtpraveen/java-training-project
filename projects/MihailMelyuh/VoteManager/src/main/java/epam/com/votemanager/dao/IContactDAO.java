package epam.com.votemanager.dao;

import java.util.List;

import epam.com.votemanager.domain.Contact;
import epam.com.votemanager.domain.Question;

public interface IContactDAO {

	public void addContact(Contact contact);

	public List<Contact> listContact();

	public void removeContact(Integer id);

	public Contact selectUserByMail(String email);

	public List<Question> answeredQuestions();

	public void vote(Integer variantId);

	public boolean isUniqueEmail(Contact contact);

}
