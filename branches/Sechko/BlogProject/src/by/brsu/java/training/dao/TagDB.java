package by.brsu.java.training.dao;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.util.HibernateUtil;

public class TagDB implements ITagDAO {

	Session session;

	private boolean isTagExists(long id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Tag.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		session.getTransaction().commit();
		return list.size() > 0;
	}
	
	private boolean isTagExists(String text) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Tag.class);
		crit.add(Restrictions.eq("text", text));
		List list = crit.list();
		session.getTransaction().commit();
		return list.size() > 0;
	}
	
	public boolean addTag(Tag tag) {
		if (isTagExists(tag.getText())) {
			return false;
		}
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(tag);
		session.getTransaction().commit();
		return true;
	}

	public boolean deleteTag(long id) {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Tag.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		if (list.size() == 0) {
			session.getTransaction().commit();
			return false;
		}
		Tag tag = (Tag) crit.list().get(0);
		session.delete(tag);
		session.getTransaction().commit();
		return true;
	}

	public Set<Tag> getTags() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Tag.class);
		Set<Tag> tags = new TreeSet<Tag>(crit.list());
		return tags;
	}

	public void commit() {
		if (session != null) {
			if (session.isOpen()) {
				if (session.getTransaction() != null) {
					if (session.getTransaction().isActive()) {
						session.getTransaction().commit();
					}
				}
			}
		}
	}

	public Tag getTagById(long id) {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Criteria crit = session.createCriteria(Tag.class);
		crit.add(Restrictions.idEq(id));
		List list = crit.list();
		if ( list.size() == 0) {
			commit();
			return null;
		}
		return (Tag)list.get(0);
	}
}
