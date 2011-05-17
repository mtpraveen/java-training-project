package by.brsu.java.training.service;

import by.brsu.java.training.entity.Comment;

public interface ICommentService {
	boolean addComment(long articleId, Comment comment);
	Comment getCommentById(long commentId);
	void setComment(Comment comment);
	boolean deleteComment(long commentId);
}
