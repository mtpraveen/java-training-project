package by.brsu.java.training.test;

import java.util.Date;

import org.junit.Test;

import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.service.ServiceDBFactory;

public class CommentServiceTest {
	@Test
	public void addCommentTest() {
		Comment comment = new Comment(1, "Ivanov", new Date(),
				"This is a text comment", false);
		ServiceDBFactory.getCommentServiceDb().addComment(1, comment);
		
		// comment.setAuthor("Ivanov");
		// comment.setDate(new Date());
		// comment.setPublished(false);
		// comment.setText("This is a test comment");
		// comment.set

	}
}
