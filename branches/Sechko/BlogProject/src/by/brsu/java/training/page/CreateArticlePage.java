package by.brsu.java.training.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.junit.internal.ArrayComparisonFailure;

import sun.security.action.GetBooleanAction;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.entity.User;
import by.brsu.java.training.service.TagService;
import by.brsu.java.training.service.UserService;

public class CreateArticlePage extends HomePage {

	public static final String SELECT_BLOG_FIELD = "selectBlog";
	public static final String ARTICLE_TITLE_FIELD = "titleField";
	public static final String ARTICLE_TEXT_FIELD = "textField";
	public static final String SELECT_TAGS_FIELD = "selectTags";
	Form articleForm;
	String articleMessage = "";
	Select selectBlog;
	long currentBlogId;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.click.Page#onSecurityCheck()
	 */
	@Override
	public boolean onSecurityCheck() {
		if (!isUserAutorized()) {
			setRedirect("/bloggers-page.htm");
			return false;
		}
		return true;
	}
	
	protected Set<Tag> getSelectedTags(Form form) {
		Select selectTags = (Select) form
				.getField(SELECT_TAGS_FIELD);
		List<String> tagsStrings = selectTags.getSelectedValues();
		Set<Tag> tags = new TreeSet<Tag>();
		if (tagsStrings != null) {
			for (String tagString : tagsStrings) {
				try {
				long tagId = Long.parseLong(tagString);
				Tag tag = TagService.getInstance()
						.getTagById(tagId);
				tags.add(tag);
				} catch (NumberFormatException ex) {
					return null;
				}
			}
		}
		return tags;
	}

	private boolean checkArticle(String blogTitle, Article article) {
		long userId = (Long) getContext().getSessionAttribute(ID_ATTRIBUTE);
		if (!UserService.getInstance().isBlogExists(userId, blogTitle)) {
			getContext().setRequestAttribute("articleMessage",
					String.format("Blog %s is not exists", blogTitle));
			setForward("/create-article-page.htm");
			return false;
		}
		Blog blog = UserService.getInstance().getBlogByTitle(userId, blogTitle);
		currentBlogId = blog.getId();
		if (UserService.getInstance().getArticleByTitle(blog.getId(),
				article.getTitle()) != null) {
			getContext()
					.setRequestAttribute(
							"articleMessage",
							String
									.format(
											"Article with title %s is already exists in blog %s",
											article.getTitle(), blogTitle));
			setForward("/create-article-page.htm");
			return false;
		}
		String author = (String) getContext().getSessionAttribute(
				USERNAME_ATTRIBUTE);
		if (author != null) {
			article.setAuthor(author);
		}
		return addArticle(userId, blog.getId(), article);
	}

	private boolean addArticle(long userId, long blogId, Article article) {
		Set<Tag> tags = article.getTags();
		article.setId(0);
		article.setComments(null);
		article.setTags(null);
		article.setBlog(null);
		return UserService.getInstance().addArticle(blogId, article)
				&& UserService.getInstance().setTags(article.getId(), tags);
	}

	private Form createArticleForm() {
		articleForm = new Form("articleForm");
		articleForm.add(createSelectBlog());
		articleForm.add(createTitleField());
		articleForm.add(createSelectTags());
		articleForm.add(createTextField());
		Submit submit = new Submit("submit");
		submit.setLabel("Create article");
		articleForm.add(submit);
		submit.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (articleForm.isValid()) {
					String blogTitle = articleForm
							.getFieldValue(SELECT_BLOG_FIELD);
					String title = articleForm
							.getFieldValue(ARTICLE_TITLE_FIELD);
					String text = articleForm.getFieldValue(ARTICLE_TEXT_FIELD);

					Article article = new Article(0, title, null, new Date(),
							text, getSelectedTags(articleForm), null, false, null);
					if (checkArticle(blogTitle, article)) {
						String link = String.format("/blog-page.htm?%s=%s",
								BLOG_ID, currentBlogId);
						setRedirect(link);
					}
				}
				return false;
			}
		});
		return articleForm;
	}

	protected Select createSelectBlog() {
		selectBlog = new Select(SELECT_BLOG_FIELD);
		selectBlog.setRequired(false);
		selectBlog.setLabel("Choose blog");
		selectBlog.setWidth("100%");
		Long userId = (Long) getContext().getSessionAttribute(ID_ATTRIBUTE);
		if (userId == null) {
			return selectBlog;
		}
		User user = UserService.getInstance().getUserById(userId);
		String defaultBlogTitle = getContext().getRequestParameter(
				SELECT_BLOG_FIELD);
		if (defaultBlogTitle != null) {
			selectBlog.setValue(defaultBlogTitle);
		}
		if (user != null && user.getBlogs() != null) {
			for (Blog blog : user.getBlogs()) {
				Option blogOption = new Option(blog.getTitle());
				selectBlog.add(blogOption);
			}
		}
		return selectBlog;
	}

	protected TextField createTitleField() {
		TextField titleField = new TextField(ARTICLE_TITLE_FIELD, true);
		titleField.setLabel("Title");
		titleField.setMinLength(1);
		titleField.setMaxLength(100);
		titleField.setWidth("100%");
		String title = getContext().getRequestParameter(ARTICLE_TITLE_FIELD);
		if (title != null) {
			titleField.setValue(title);
		}
		return titleField;
	}

	protected TextArea createTextField() {
		TextArea textField = new TextArea(ARTICLE_TEXT_FIELD, true);
		textField.setLabel("Text");
		textField.setWidth("795px");
		
		textField.setRows(20);
		String text = getContext().getRequestParameter(ARTICLE_TEXT_FIELD);
		if (text != null) {
			textField.setValue(text);
		}
		return textField;
	}

	protected Select createSelectTags() {
		Select selectTags = new Select(SELECT_TAGS_FIELD);
		selectTags.setLabel("Tags");
		selectTags.setMultiple(true);
		Set<Tag> tags = TagService.getInstance().getTags();
		selectTags.setSize(tags.size() / 2);
		selectTags.setWidth("100%");
		for (Tag tag : tags) {
			Option option = new Option(tag.getId(), tag.getText());
			selectTags.add(option);
		}
		String[] selectedStrings = getContext().getRequestParameterValues(
				SELECT_TAGS_FIELD);
		List<String> selectedStringsList = new ArrayList<String>();
		if (selectedStrings != null) {
			for (String text : selectedStrings) {
				selectedStringsList.add(text);
			}
			selectTags.setSelectedValues(selectedStringsList);
		}
		return selectTags;
	}

	private String createArticleMessage() {
		String message = (String) getContext().getRequestAttribute(
				"articleMessage");
		if (message != null) {
			articleMessage = message;
		}
		return articleMessage;
	}

	public CreateArticlePage() {
		addModel("articleMessage", createArticleMessage());
		addControl(createArticleForm());

	}

}