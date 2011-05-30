package by.brsu.java.training.page;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionButton;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Label;
import org.apache.click.control.PageLink;
import org.apache.click.control.Table;
import org.apache.click.extras.control.HtmlFieldSet;
import org.apache.click.extras.control.HtmlForm;

import java.util.Calendar;

import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.service.UserService;
import by.brsu.java.training.util.DateUtil;
import by.brsu.java.training.util.UserInfo;

public class BloggersPage extends HomePage {

	private String bloggersMessage = "";
	List<HtmlFieldSet> bloggersList;
	int bloggersCount = 0;

	private List<HtmlFieldSet> createBloggersList() {
		bloggersList = new ArrayList<HtmlFieldSet>();
		Set<UserInfo> users = UserService.getInstance().getUserInfo();
		for (UserInfo userInfo : users) {
			HtmlFieldSet htmlFieldSet = new HtmlFieldSet("blogger");
			
			String bloggerLink = String.format(
					"<a href=blogger-page.htm?%s=%s>%s</a>", BLOGGER_NAME,
					userInfo.getName(), userInfo.getName());
			Label bloggerName = new Label("bloggerName");
			bloggerName.setLabel(bloggerLink);
			htmlFieldSet.add(bloggerName);
			
			Label date = new Label("date");
			date.setLabel(DateUtil.dateFormat(userInfo.getDate()));
			htmlFieldSet.add(date);
			bloggersList.add(htmlFieldSet);
		}
		bloggersCount = bloggersList.size();
		
		return bloggersList;
	}

	private String createBloggersMessage() {
		if (bloggersList.size() == 0) {
			bloggersMessage = "На сайте пока нет блоггеров";
		}
		return bloggersMessage;
	}

	public BloggersPage() {
		addModel("bloggersList", createBloggersList());
		addModel("bloggersCount", bloggersCount);
		addModel("bloggersMessage", createBloggersMessage());
	}

}