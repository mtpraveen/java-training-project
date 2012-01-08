package by.epam.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.dao.CommentRepository;
import by.epam.blog.model.Comment;

/**
 * @author Dmitry_Goncharov
 * 
 */
@Service
@Transactional
public class CommentServiceImpl {
	@Autowired
	private CommentRepository commentRepository;

	public Comment findCommentById(Long comment) {
		return commentRepository.findOne(comment);
	}

	public Long saveComment(Comment comment) {
		return commentRepository.save(comment).getId();
	}
}
