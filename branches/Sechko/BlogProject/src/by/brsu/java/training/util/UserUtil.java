package by.brsu.java.training.util;

import java.util.Set;
import java.util.TreeSet;

import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.User;

public class UserUtil {
	public static User copyUser(User otherUser) {
		if (otherUser == null ) {
			return null;
		}
		TreeSet<Blog> blogs = new TreeSet<Blog>();
		blogs.addAll(otherUser.getBlogs());
		return new User(otherUser.getId(), otherUser.getName(), otherUser
				.getPassword(), otherUser.getDate(), otherUser.getAbout(),
				blogs, otherUser.isModerator(), otherUser.isBanned());
	}

	public static Set<User> copyUsers(Set<User> users) {
		Set<User> usersCopy = new TreeSet<User>();
		for (User user : users) {
			usersCopy.add(copyUser(user));
		}
		return usersCopy;
	}
}
