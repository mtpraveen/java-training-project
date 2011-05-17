package by.brsu.java.training.service;

import java.util.List;

import by.brsu.java.training.entity.Tag;

public interface ITagService {
	boolean addTag(Tag tag);

	Tag getTagById(long tagId);

	List<Tag> getTags();

	boolean deleteTag(long tagId);
}
