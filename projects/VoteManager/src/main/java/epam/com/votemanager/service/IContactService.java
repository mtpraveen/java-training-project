package epam.com.votemanager.service;

import java.util.List;

import epam.com.votemanager.domain.Contact;
import epam.com.votemanager.domain.Question;

public interface IContactService {

    public void addContact(Contact contact);

    public List<Contact> listContact();

    public void removeContact(Integer id);
    
    public List<Question> questionForCurrentUser();
    
    public void vote(Integer variantId);

	public boolean isUniqueEmail(Contact contact);
}