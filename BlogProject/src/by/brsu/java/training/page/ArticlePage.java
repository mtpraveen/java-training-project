package by.brsu.java.training.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.ActionButton;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Field;
import org.apache.click.control.FieldSet;
import org.apache.click.control.Form;
import org.apache.click.control.Label;
import org.apache.click.control.PageLink;
import org.apache.click.control.Panel;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.extras.control.HtmlFieldSet;
import org.eclipse.jdt.internal.compiler.ast.LabeledStatement;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Comment;
import by.brsu.java.training.service.UserService;
import by.brsu.java.training.util.ArticleUtil;
import by.brsu.java.training.util.DateUtil;

public class ArticlePage extends HomePage {

	public static final String BUTTON_WIDTH = "140";
	
	ActionButton deleteArticleButton;
	ActionButton editArticleButton;
	Article article;
	String articleSummary = "";
	PageLink authorLink;
	List<HtmlFieldSet> commentsList;
	Form addCommentForm;
	int commentsCount = 0;

	private Article getArticle() {
		long articleId = 0;
		Object requestParam = getContext().getRequestParameter(ARTICLE_ID);
		Object sessionParam = getContext().getSessionAttribute(ARTICLE_ID);
		if (requestParam == null && sessionParam == null) {
			setRedirect("/bloggers-page.htm");
			return null;
		}
		if (requestParam != null) {
			articleId = Long.parseLong((String) getContext()
					.getRequestParameter(ARTICLE_ID));
		} else if (sessionParam != null) {
			articleId = (Long) getContext().getSessionAttribute(ARTICLE_ID);
		}
		article = UserService.getInstance().getArticleById(articleId);
		if (article != null) {
			getContext().setSessionAttribute(ARTICLE_ID, article.getId());
		} else {
			setRedirect("/bloggers-page.htm");
			return null;
		}
		return article;
	}

	private ActionButton createEditArticleButton() {
		if (isUserAutorized() && isUserHasArticle(article)) {
			editArticleButton = new ActionButton("editArticleButton");
			editArticleButton.setLabel("Edit this article");
			editArticleButton.setWidth(BUTTON_WIDTH);
			editArticleButton.setActionListener(new ActionListener() {

				public boolean onAction(Control source) {
					if (isUserAutorized() && isUserHasArticle(article)) {
						String link = String.format(
								"/edit-article-page.htm?articleId=%d", article
										.getId());
						setRedirect(link);
					}
					return false;
				}
			});
		}
		return editArticleButton;
	}

	private ActionButton createDeleteArticleButton() {
		if (isUserAutorized() && isUserHasArticle(article)) {
			deleteArticleButton = new ActionButton("deleteArticleButton");
			deleteArticleButton.setLabel("Delete this article");
			deleteArticleButton.setWidth(BUTTON_WIDTH);
			deleteArticleButton.setActionListener(new ActionListener() {

				public boolean onAction(Control source) {

					if (isUserAutorized() && isUserHasArticle(article)) {
						UserService.getInstance()
								.deleteArticle(article.getId());
						String name = (String) getContext()
								.getSessionAttribute(USERNAME_ATTRIBUTE);
						String link = String.format("/blogger-page.htm?%s=%s",
								BLOGGER_NAME, name);
						getContext().setSessionAttribute(BLOG_ID, null);
						setRedirect(link);
					}
					return false;
				}
			});

		}
		return deleteArticleButton;
	}

	private PageLink createAuthorLink(String author) {
		authorLink = new PageLink("authorLink", BloggerPage.class);
		authorLink.setLabel(author);
		authorLink.setParameter(BLOGGER_NAME, author);
		return authorLink;
	}

	private List<HtmlFieldSet> createCommentsList() {
		commentsList = new ArrayList<HtmlFieldSet>();
		TreeSet<Comment> comments = new TreeSet<Comment>(article.getComments());
		for (Comment comment : comments) {
			// if (comment.isPublished()) {
			HtmlFieldSet htmlFieldSet = new HtmlFieldSet("comment");
			htmlFieldSet.setShowBorder(false);
			String authorLink = String.format(
					"<a href=blogger-page.htm?%s=%s>%s</a>", BLOGGER_NAME, comment
							.getAuthor(), comment.getAuthor());
			Label author = new Label("author");
			author.setLabel(authorLink);
			htmlFieldSet.add(author);
			Label date = new Label("date");
			date.setLabel(DateUtil.dateFormat(comment.getDate()));
			htmlFieldSet.add(date);
			Label text = new Label("text");
			text.setLabel(comment.getText());
			htmlFieldSet.add(text);
			commentsList.add(htmlFieldSet);
			// }
		}
		commentsCount = commentsList.size();
		return commentsList;
	}

	private Form createAddCommentForm() {
		if (!isUserAutorized()) {
			return null;
		}
		addCommentForm = new Form("addCommentForm");
		TextArea textField = new TextArea("textField", true);
		textField.setLabel("The comment text");
		textField.setMinLength(1);
		textField.setMaxLength(2000);
		textField.setCols(80);
		textField.setRows(10);
		addCommentForm.add(textField);
		Submit submit = new Submit("add");
		submit.setLabel("Add comment");
		addCommentForm.add(submit);
		submit.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (isUserAutorized()) {
					if (addCommentForm.isValid()) {
						String author = (String) getContext()
								.getSessionAttribute(USERNAME_ATTRIBUTE);
						String text = addCommentForm.getFieldValue("textField");
						Comment comment = new Comment(0, author, new Date(),
								text, false, null);
						long articleId = (Long) getContext()
								.getSessionAttribute(ARTICLE_ID);
						UserService.getInstance()
								.addComment(articleId, comment);
						String link = String.format("/article-page.htm?%s=%d",
								ARTICLE_ID, articleId);
						setRedirect(link);
					}
				}
				return false;
			}
		});
		return addCommentForm;
	}

	public ArticlePage() {
		if (getArticle() == null) {
			setRedirect("/bloggers-page.htm");
		} else {
			addModel("title", article.getTitle());
			addControl(createAuthorLink(article.getAuthor()));
			addModel("date", DateUtil.dateFormat(article.getDate()));
			addModel("text", article.getText().replaceAll("[\n]", "<br>"));

			addModel("tags", ArticleUtil.createTagString(article.getTags()));
			if (createDeleteArticleButton() != null) {
				addControl(deleteArticleButton);
			}
			if (createEditArticleButton() != null) {
				addControl(editArticleButton);
			}

			addModel("commentsList", createCommentsList());
			if (createAddCommentForm() != null) {
				addControl(addCommentForm);
			}
			addModel("commentsCount", String.format("%d", commentsCount));
		}
	}

}