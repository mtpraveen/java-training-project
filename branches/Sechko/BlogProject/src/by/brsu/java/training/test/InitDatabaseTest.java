package by.brsu.java.training.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import by.brsu.java.training.dao.TagDB;
import by.brsu.java.training.dao.UserDB;
import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.util.BlogUtil;
import by.brsu.java.training.util.PasswordUtil;

/**
 * 
 * creates some users wuth his blogs and articles and prepare bloghost database
 * for work
 */
public class InitDatabaseTest {

	@Test
	public void cleanDatabase() {
		UserDB userDB = new UserDB();

		Set<User> users = userDB.getUsers();
		userDB.commit();
		for (User user : users) {
			userDB.deleteUser(user.getId());
		}

		TagDB tagDB = new TagDB();
		Set<Tag> tags = tagDB.getTags();
		tagDB.commit();
		for (Tag tag : tags) {
			tagDB.deleteTag(tag.getId());
		}
	}

	@Test
	public void createTags() {
		TagDB tagDB = new TagDB();
		Tag tag1 = new Tag(0, "C++");
		Tag tag2 = new Tag(0, "Java");
		Tag tag3 = new Tag(0, "Pascal");
		Tag tag4 = new Tag(0, "C#");
		Tag tag5 = new Tag(0, "Assembler");
		Tag tag6 = new Tag(0, "Linux");
		Tag tag7 = new Tag(0, "Eclipse");
		Tag tag8 = new Tag(0, "C--");
		Tag tag9 = new Tag(0, "Framework");
		Tag tag10 = new Tag(0, "Programming");
		Tag tag11 = new Tag(0, "Fortran");
		Tag tag12 = new Tag(0, "LISP");

		tagDB.addTag(tag1);
		tagDB.addTag(tag2);
		tagDB.addTag(tag3);
		tagDB.addTag(tag4);
		tagDB.addTag(tag5);
		tagDB.addTag(tag6);
		tagDB.addTag(tag7);
		tagDB.addTag(tag8);
		tagDB.addTag(tag9);
		tagDB.addTag(tag10);
		tagDB.addTag(tag11);
		tagDB.addTag(tag12);
	}

	@Test
	public void createUsers() {
		UserDB userDB = new UserDB();

		User user = new User(0, "user1", PasswordUtil.hashString("user1"),
				new Date(), "About user1", null, false, false);
		userDB.addUser(user);
		user = new User(0, "user2", PasswordUtil.hashString("user2"),
				new Date(), "About user2", null, false, true);
		userDB.addUser(user);
		user = new User(0, "user3", PasswordUtil.hashString("user3"),
				new Date(), "About user3", null, true, false);
		userDB.addUser(user);
	}

	@Test
	public void createArticles() {
		UserDB userDB = new UserDB();
		Blog blog = new Blog(0, "My first blog", "user1", new Date(), null,
				null);
		userDB.addBlog(1, blog);

		String text = "Apache Click is a simple JEE web application framework for commercial Java developers.";
		Set<Tag> tags = new TreeSet<Tag>();
		tags.add(new Tag(2, "Java"));

		Article article = new Article(0, "Introduction to Apache Click",
				"user1", new Date(), text, null, null, true, null);
		userDB.addArticle(1, article);
		userDB.setTags(1, tags);
	}

	@Test
	public void createComments() {
		UserDB userDB = new UserDB();
		String text = "Click not supports MVC directly";
		Comment comment1 = new Comment(0, "user3", new Date(), text, true, null);
		text = "But Click has many standart controls for different tasks";
		Comment comment2 = new Comment(0, "user2", new Date(), text, false,
				null);
		userDB.addComment(1, comment1);
		userDB.addComment(1, comment2);
	}

	@Test
	public void getBlogByIdTest() {
		UserDB userDB = new UserDB();
		// Blog blog = BlogUtil.copyBlog(userDB.getBlogById(1));
		Blog blog = userDB.getBlogById(1);
		assertNotNull(blog);
	}
}
