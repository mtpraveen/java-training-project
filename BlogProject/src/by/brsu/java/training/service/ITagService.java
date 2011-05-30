package by.brsu.java.training.service;

import java.util.Set;

import by.brsu.java.training.entity.Tag;

public interface ITagService {
	Set<Tag> getTags();
	Tag getTagById(long id);
}
