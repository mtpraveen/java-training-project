package by.brsu.java.training.util;

import java.util.Set;
import java.util.TreeSet;

import by.brsu.java.training.entity.Tag;

public class TagUtil {
	public static Tag copyTag(Tag tag) {
		Tag tagCopy = new Tag(tag.getId(), tag.getText());
		return tagCopy;
	}

	public static Set<Tag> copyTags(Set<Tag> tags) {
		Set<Tag> tagsCopy = new TreeSet<Tag>();
		for (Tag tag : tags) {
			tagsCopy.add(copyTag(tag));
		}
		return tagsCopy;
	}

}
