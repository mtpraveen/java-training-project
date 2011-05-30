package by.brsu.java.training.dao;

import java.util.Set;

import by.brsu.java.training.entity.Tag;

public interface ITagDAO {
	boolean addTag(Tag tag);
	Set<Tag> getTags();
	Tag getTagById(long id);
	boolean deleteTag(long id);
	void commit();
}
