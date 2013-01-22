package epam.com.votemanager.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import epam.com.votemanager.domain.Contact;
import epam.com.votemanager.domain.Group;
import epam.com.votemanager.domain.KeyForVote;
import epam.com.votemanager.domain.Question;
import epam.com.votemanager.domain.Variant;
import epam.com.votemanager.domain.Vote;

@Repository
public class ContactDAO implements IContactDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addContact(Contact contact) {
		Integer groupId = 2;
		Group group = (Group) sessionFactory.getCurrentSession().load(
				Group.class, groupId);
		if (group != null) {
			List<Group> groups = new ArrayList<Group>();
			groups.add(group);
			contact.setGroups(groups);
		}
		contact.setEnabled(true);
		contact.setLocked(false);
		contact.setPassword(passwordEncoder.encodePassword(
				contact.getPassword(), contact.getEmail()));
		sessionFactory.getCurrentSession().save(contact);
	}

	@SuppressWarnings("unchecked")
	public List<Contact> listContact() {

		return sessionFactory.getCurrentSession().createQuery("from Contact")
				.list();
	}

	public void removeContact(Integer id) {
		Contact contact = (Contact) sessionFactory.getCurrentSession().load(
				Contact.class, id);
		if (null != contact) {
			sessionFactory.getCurrentSession().delete(contact);
		}
	}

	@Override
	public Contact selectUserByMail(String email) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + Contact.class.getName() + " where email = :email ");
		query.setParameter("email", email);
		@SuppressWarnings("rawtypes")
		List objects = query.list();

		Contact contact = (Contact) objects.get(0);
		contact.getAuthorities();
		return contact;
	}

	@Override
	public List<Question> answeredQuestions() {
		Session currentSession = sessionFactory.getCurrentSession();
		Object currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userName;
		if (currentUser instanceof UserDetails) {
			userName = ((UserDetails) currentUser).getUsername();
		} else {
			userName = currentUser.toString();
		}
		List<Question> questions = new ArrayList<Question>();
		if (!userName.equalsIgnoreCase("guest")) {
			Query query = currentSession.createQuery("from "
					+ Contact.class.getName() + " where email = :email ");
			query.setParameter("email", userName);
			@SuppressWarnings("rawtypes")
			List objects = query.list();
			Contact contact = (Contact) objects.get(0);
			questions = contact.getQuestions();
		}
		return questions;

	}

	@Override
	public void vote(Integer variantId) {
		Object currentUser = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String userName;
		if (currentUser instanceof UserDetails) {
			userName = ((UserDetails) currentUser).getUsername();
		} else {
			userName = currentUser.toString();
		}
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + Contact.class.getName() + " where email = :email ");
		query.setParameter("email", userName);
		@SuppressWarnings("rawtypes")
		List objects = query.list();
		Contact contact = (Contact) objects.get(0);
		Variant variant = (Variant) currentSession.load(Variant.class,
				variantId);
		KeyForVote key = new KeyForVote(contact.getId(), variantId, variant
				.getQuestion().getId());
		System.out.println(contact.getId());
		System.out.println(variantId);
		System.out.println(variant.getQuestion().getId());
		Vote vote = new Vote();
		vote.setKey(key);
		currentSession.save(vote);
	}

	public boolean isUniqueEmail(Contact contact) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + Contact.class.getName() + " where email = :email ");
		query.setParameter("email", contact.getEmail());
		@SuppressWarnings("rawtypes")
		List objects = query.list();
		return (objects.isEmpty());
	}

}