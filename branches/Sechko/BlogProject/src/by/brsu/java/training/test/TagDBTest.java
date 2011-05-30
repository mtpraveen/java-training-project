package by.brsu.java.training.test;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.TreeSet;

import org.hibernate.hql.ast.tree.ExpectedTypeAwareNode;
import org.junit.Test;

import by.brsu.java.training.dao.TagDB;
import by.brsu.java.training.entity.Tag;

/**
 * Tag DAO implementation test
 *
 */
public class TagDBTest {
	@Test
	public void addTagTest() {
		TagDB tagDB = new TagDB();
		Tag tag = new Tag(0, "Tag1");
		assertTrue(tagDB.addTag(tag));
		assertFalse(tagDB.addTag(tag));
		tag.setText("Tag2");
		assertTrue(tagDB.addTag(tag));
	}

	@Test
	public void getTagByIdTest() {
		TagDB tagDB = new TagDB();
		Tag expectedTag = new Tag(1, "Tag1");
		Tag actualTag = tagDB.getTagById(1);
		assertEquals(expectedTag.getId(), actualTag.getId());
		assertEquals(expectedTag.getText(), actualTag.getText());
		assertNull(tagDB.getTagById(444));
	}

	@Test
	public void deleteTagTest() {
		TagDB tagDB = new TagDB();
		assertTrue(tagDB.deleteTag(1));
		assertFalse(tagDB.deleteTag(500));
	}	
	
	@Test
	public void getTagsTest() {
		TagDB tagDB = new TagDB();
		TreeSet<Tag> expectedTags = new TreeSet<Tag>();
		Tag tag = new Tag(2, "Tag2");
		expectedTags.add(tag);
		
		Set<Tag> actualTags = tagDB.getTags();
		Tag tag1 = expectedTags.iterator().next();
		Tag tag2 = actualTags.iterator().next();
		assertEquals(tag1.getId(), tag2.getId());
		assertEquals(tag1.getText(), tag2.getText());
	}
}
