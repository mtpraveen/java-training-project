package by.brsu.java.training.service;

import org.hibernate.Session;

import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.util.SessionUtil;

public class CommentServiceDB implements ICommentService {

	public boolean addComment(long articleId, Comment comment) {
		Session session = null;
		try {
			session = SessionUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return false;
	}

	public boolean deleteComment(long commentId) {
		// TODO Auto-generated method stub
		return false;
	}

	public Comment getCommentById(long commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setComment(Comment comment) {
		// TODO Auto-generated method stub

	}

}
