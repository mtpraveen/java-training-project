package by.brsu.java.training.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Form;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Tag;
import by.brsu.java.training.service.TagService;
import by.brsu.java.training.service.UserService;

public class EditArticlePage extends CreateArticlePage {

	Article article;
	Form editForm;

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
			getContext().setSessionAttribute(ARTICLE_ID, articleId);
		} else {
			setRedirect("/bloggers-page.htm");
			return null;
		}
		return article;
	}

	private Form createEditForm() {
		editForm = new Form("editForm");

		TextField titleField = super.createTitleField();
		titleField.setDisabled(true);
		titleField.setValue(article.getTitle());
		titleField.setRequired(false);
		titleField.setWidth("100%");
		editForm.add(titleField);

		Select selectTags = super.createSelectTags();
		if (getContext().getRequestParameter(SELECT_TAGS_FIELD) == null) {
			List<String> selectedStringsList = new ArrayList<String>();
			for (Tag tag : article.getTags()) {
				selectedStringsList.add(tag.getText());
			}
			selectTags.setSelectedValues(selectedStringsList);
		}
		editForm.add(selectTags);

		TextArea textField = super.createTextField();
		textField.setWidth("850px");
		if (getContext().getRequestParameter(ARTICLE_TEXT_FIELD) == null) {
			textField.setValue(article.getText());
		}
		editForm.add(textField);

		Submit submit = new Submit("submit");
		submit.setLabel("Apply changes");
		editForm.add(submit);
		submit.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (editForm.isValid()) {
					String articleText = editForm
							.getFieldValue(ARTICLE_TEXT_FIELD);
					article.setText(articleText);
					article.setTags(getSelectedTags(editForm));
					UserService.getInstance().editArticle(article);

					String link = String.format("/article-page.htm?%s=%d",
							ARTICLE_ID, article.getId());
					setRedirect(link);

				}
				return false;

			}
		});
		return editForm;
	}

	public EditArticlePage() {
		getArticle();
		addControl(createEditForm());
	}

}