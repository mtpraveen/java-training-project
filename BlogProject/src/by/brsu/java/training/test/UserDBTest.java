package by.brsu.java.training.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import by.brsu.java.training.dao.UserDB;
import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.util.UserUtil;

/**
 * 
 * User DAO implementation test
 */
public class UserDBTest {

	@Test
	public void addUserTest() {
		User user = new User(0, "Ivanov", "12345", new Date(), "About me",
				null, false, false);
		new UserDB().addUser(user);
	}

	@Test
	public void getUserByIdTest() {
		User actualUser = new User(0, "Petrov", "12345", new Date(),
				"About me", new TreeSet<Blog>(), false, false);
		UserDB userDB = new UserDB();
		userDB.addUser(actualUser);
		User expectedUser = userDB.getUserById(2);

		assertEquals(actualUser, UserUtil.copyUser(expectedUser));
		userDB.commit();
		assertNull(userDB.getUserById(33));
		userDB.commit();
	}

	@Test
	public void getUserByNameTest() {
		long userId = 1;
		UserDB userDB = new UserDB();
		User expectedUser = userDB.getUserByName("Ivanov");
		assertEquals(userId, expectedUser.getId());
		userDB.commit();
		assertNull(new UserDB().getUserByName("Sidorov"));
	}

	@Test
	public void changeUserTest() {
		UserDB userDB = new UserDB();
		User user = userDB.getUserById(1);
		user.setName("Sidorov");
		User expectedUser = new User(1, "Sidorov", "12345", new Date(),
				"About me", new TreeSet<Blog>(), false, false);
		userDB.commit();
		User actualUser = userDB.getUserById(1);
		assertEquals(actualUser.getId(), expectedUser.getId());
		userDB.commit();

		actualUser = userDB.getUserById(2);
		Blog blog = new Blog(0, "My First Blog", "user1", new Date(), null, null);
		expectedUser.getBlogs().add(blog);

		actualUser.getBlogs().add(blog);
		userDB.commit();
		expectedUser = new User(2, "Petrov", "12345", new Date(), "About me",
				new TreeSet<Blog>(), false, false);
		expectedUser.getBlogs().add(blog);
		assertEquals(expectedUser, UserUtil.copyUser(userDB.getUserById(2)));
	}

	@Test
	public void getUsersTest() {
		UserDB userDB = new UserDB();

		User user1 = new User(1, "Sidorov", "12345", new Date(), "About me",
				new TreeSet<Blog>(), false, false);

		User user2 = new User(2, "Petrov", "12345", new Date(), "About me",
				new TreeSet<Blog>(), false, false);
		userDB.addUser(user2);
		Set<User> actualUsers = new TreeSet<User>();
		actualUsers.add(user1);
		actualUsers.add(user2);
		Set<User> users = userDB.getUsers();
		assertEquals(actualUsers, users);
		userDB.commit();
	}

	@Test
	public void addBlogTest() {
		UserDB userDB = new UserDB();
		assertTrue(userDB.addBlog(1, new Blog(0, "Blog Title", "user1", new Date(),
				null, null)));
		assertFalse(userDB.addBlog(1, new Blog(0, "Blog Title", "user1", new Date(),
				null, null)));

	}

	@Test
	public void deleteBlogTest() {
		UserDB userDB = new UserDB();
		assertTrue(userDB.deleteBlog(1));
		assertNull(userDB.getBlogById(1));
		assertFalse(userDB.deleteBlog(1));
	}

	@Test
	public void getBlogByIdTest() {
		UserDB userDB = new UserDB();
		Blog expectedBlog = new Blog(2, "Blog Title", "user1", new Date(),
				new TreeSet<Article>(), null);
		assertNotNull(userDB.getBlogById(2));
		assertNull(userDB.getBlogById(1));
		Blog actualBlog = userDB.getBlogById(2);
		assertEquals(expectedBlog.getId(), actualBlog.getId());
		assertEquals(expectedBlog.getTitle(), actualBlog.getTitle());
		assertEquals(expectedBlog.getArticles(), actualBlog.getArticles());
		userDB.commit();
	}

	@Test
	public void getBlogByTitleTest() {
		UserDB userDB = new UserDB();
		Blog actualBlog = userDB.getBlogByTitle(1, "Blog Title");
		assertEquals("Blog Title", actualBlog.getTitle());
		assertNull(userDB.getBlogByTitle(1, "qwerty"));
		userDB.commit();
	}

	@Test
	public void changeBlogTest() {
		UserDB userDB = new UserDB();
		Blog actualBlog = userDB.getBlogByTitle(1, "Blog Title");
		actualBlog.setTitle("New Blog Title");
		userDB.commit();
		assertNotNull(userDB.getBlogByTitle(1, "New Blog Title"));
	}

	@Test
	public void addArticleTest() {
		UserDB userDB = new UserDB();
		Article expectedArticle = new Article(0, "Expected Article", "User",
				new Date(), "This is a first article", null, null, false, null);
		assertTrue(userDB.addArticle(2, expectedArticle));
		expectedArticle.setId(1);
		Article actualArticle = userDB.getArticleById(1);
		assertEquals(expectedArticle.getId(), actualArticle.getId());
		assertEquals(expectedArticle.getAuthor(), actualArticle.getAuthor());
		assertEquals(expectedArticle.getTitle(), actualArticle.getTitle());
		assertNull(userDB.getArticleById(2));
		expectedArticle = new Article(0, "Second article", "User", new Date(),
				"This is a first article", null, null, false, null);
		userDB.addArticle(2, expectedArticle);
		assertNotNull(userDB.getArticleById(2));
	}

	@Test
	public void getArticleByTitleTest() {
		UserDB userDB = new UserDB();
		Article expectedArticle = new Article(1, "Expected Article", "User",
				new Date(), "This is a first article", null, null, false, null);
		Article actualArticle = userDB.getArticleByTitle(2, "Expected Article");
		assertNull(userDB.getArticleByTitle(22, "Expected Article"));
		assertNull(userDB.getArticleByTitle(1, "Invalid title"));
		assertEquals(expectedArticle.getId(), actualArticle.getId());
	}

	@Test
	public void changeArticleTest() {
		UserDB userDB = new UserDB();
		Article article = userDB.getArticleById(1);
		article.setText("New article text");
		userDB.commit();
		article = userDB.getArticleById(1);
		assertEquals("New article text", article.getText());
	}

	@Test
	public void deleteArticleTest() {
		UserDB userDB = new UserDB();
		assertFalse(userDB.deleteArticle(23131));
		assertTrue(userDB.deleteArticle(2));
		assertNull(userDB.getArticleById(2));
	}

	@Test
	public void addCommentTest() {
		UserDB userDB = new UserDB();
		Comment comment = new Comment(0, "User1", new Date(),
				"This is a comment text", false, null);
		assertTrue(userDB.addComment(1, comment));
		assertFalse(userDB.addComment(222, comment));
		assertTrue(userDB.addComment(1, comment));
	}

	@Test
	public void getCommentByIdTest() {
		UserDB userDB = new UserDB();
		Comment expectedComment = new Comment(1, "User1", new Date(),
				"This is a comment text", false, null);
		Comment actualComment = userDB.getCommentById(1);
		assertEquals(expectedComment.getId(), actualComment.getId());
		assertEquals(expectedComment.getText(), actualComment.getText());
		assertEquals(expectedComment.getAuthor(), actualComment.getAuthor());
		assertNull(userDB.getCommentById(222));
	}
	
//	@Test 
//	public void setTagsTest() {
//		UserDB userDB = new UserDB();
//		Article article = userDB.getArticleById(1);
//		Set<Tag> tags = new TreeSet<Tag>();
//		Tag expectedTag = new Tag(0, "Java");
//		tags.add(expectedTag);
//		article.setTags(tags);
//		userDB.commit();
//		article = userDB.getArticleById(1);
//		Tag actualTag = article.getTags().iterator().next();
//		expectedTag.setId(1);
//		assertEquals(expectedTag.getId(), actualTag.getId());
//		assertEquals(expectedTag.getText(), actualTag.getText());
//	}

	 @Test
	 public void deleteUserTest() {
	 UserDB userDB = new UserDB();
	 userDB.addUser(new User(1, "Titov", "12345", new Date(), "About Titov",
	 new TreeSet<Blog>(), true, false));
	 assertTrue(userDB.deleteUser(2));
	 assertNull(userDB.getUserById(2));
	 }
}
