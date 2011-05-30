package by.brsu.java.training.page;

import java.util.Date;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Form;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

import by.brsu.java.training.entity.Blog;
import by.brsu.java.training.service.UserService;

public class CreateBlogPage extends HomePage {

	Form blogForm;
	String blogMessage = "";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.click.Page#onSecurityCheck()
	 */
	@Override
	public boolean onSecurityCheck() {
		if (!isUserAutorized()) {
			setRedirect(BloggersPage.class);
			return false;
		}
		return true;
	}

	private Form createBlogForm() {
		blogForm = new Form("blogForm");
		TextField titleField = new TextField("titleField", true);
		titleField.setLabel("Blog title");
		titleField.setMinLength(1);
		titleField.setMaxLength(100);
		titleField.setWidth("825px");
		String title = getContext().getRequestParameter("titleField");
		if (title != null) {
			titleField.setValue(title);
			titleField.setFocus(true);
		}
		blogForm.add(titleField);
		Submit submit = new Submit("createSubmit");
		submit.setLabel("Create blog");
		blogForm.add(submit);
		submit.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (blogForm.isValid()) {
					String blogTitle = blogForm.getFieldValue("titleField");
					long userId = (Long) getContext().getSessionAttribute(
							ID_ATTRIBUTE);
					String userName = (String) getContext()
							.getSessionAttribute(USERNAME_ATTRIBUTE);
					if (UserService.getInstance().isBlogExists(userId,
							blogTitle)) {
						getContext().setRequestAttribute("blogMessage",
								"Блог с таким названием уже существует");
						setForward("/user/create-blog-page.htm");
						return false;
					}
					Blog blog = new Blog(0, blogTitle, userName, new Date(),
							null, null);
					if (!UserService.getInstance().addBlog(userId, blog)) {
						getContext().setRequestAttribute("blogMessage",
								"Ошибка при создании блога");
						setForward("/create-blog-page.htm");
						return false;
					}
					String link = String.format("/blogger-page.htm?%s=%s",
							BLOGGER_NAME, userName);
					setRedirect(link);
				}
				return false;
			}

		});

		return blogForm;
	}

	private String createBlogMessage() {
		String message = (String) getContext().getRequestAttribute(
				"blogMessage");
		if (message != null) {
			blogMessage = message;
		}
		return blogMessage;
	}

	public CreateBlogPage() {
		addControl(createBlogForm());
		addModel("blogMessage", createBlogMessage());
	}

}