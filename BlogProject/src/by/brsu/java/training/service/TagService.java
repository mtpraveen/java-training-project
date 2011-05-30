package by.brsu.java.training.service;

import java.util.Set;

import by.brsu.java.training.dao.ITagDAO;
import by.brsu.java.training.dao.IUserDAO;
import by.brsu.java.training.dao.TagDB;
import by.brsu.java.training.dao.UserDB;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.util.TagUtil;

public class TagService implements ITagService {

	private static final TagService INSTANCE = new TagService();
	private ITagDAO tagDAO = null;

	private TagService() {
	}

	public static TagService getInstance() {
		return INSTANCE;
	}

	private static ITagDAO getTagDAO() {
		if (INSTANCE.tagDAO == null) {
			INSTANCE.tagDAO = new TagDB();
		}
		return INSTANCE.tagDAO;
	}
	
	public Set<Tag> getTags() {
		return TagUtil.copyTags(getTagDAO().getTags());
	}

	public Tag getTagById(long id) {
		Tag tagCopy = TagUtil.copyTag(getTagDAO().getTagById(id));
		if ( tagCopy != null ) {
			getTagDAO().commit();
		}
		return tagCopy;
	}

}
