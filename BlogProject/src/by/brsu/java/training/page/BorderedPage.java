package by.brsu.java.training.page;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.Page;
import org.apache.click.control.ActionButton;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Button;
import org.apache.click.control.Form;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextField;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class BorderedPage extends Page {
	private static final String USERNAME_ATTRIBUTE = "username";
	private static final String BLOGGER_ROLE = "blogger";
	private static final String ROLE_ATTRIBUTE = "role";

	private Form loginForm;
	private String loginMessage = "";

	private ActionButton registerButton;

	private Form searchForm;

	private Form createSearchForm() {
		searchForm = new Form("searchForm");
		TextField text = new TextField("text");
		text.setLabel("Текст");
		text.setMaxLength(250);
		searchForm.add(text);

		TextField tags = new TextField("tags");
		tags.setLabel("Тэги");
		tags.setMaxLength(250);
		searchForm.add(tags);
		
		Submit submitSearchByText = new Submit("submitSearchByText");
//		Submit submitSearchByText = new Submit("submitSearchByText", this, "onClick");
		submitSearchByText.setLabel("Поиск по тексту");
		searchForm.add(submitSearchByText);
		submitSearchByText.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		
		
		Submit submitSearchByTags = new Submit("submitSearchByTags");
		submitSearchByTags.setLabel("Поиск по тэгам");
		searchForm.add(submitSearchByTags);
		submitSearchByTags.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		return searchForm;
	}

//	public boolean onClick() {
//		setRedirect("/registration-page.htm");
//		return true;
//	}

	
	private ActionButton createRegisterButton() {
		registerButton = new ActionButton("registerButton");
		registerButton.setLabel("Зарегистрироваться");
		registerButton.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/registration-page.htm");
				return false;
			}
		});
		return registerButton;
	}

	private Form createLoginForm() {
		loginForm = new Form("loginForm");
		if (getContext().getSessionAttribute(ROLE_ATTRIBUTE) != null) {
			Submit submitExit = new Submit("Выход");
			loginForm.add(submitExit);
			submitExit.setActionListener(new ActionListener() {

				public boolean onAction(Control source) {
					ivalidateUser();
					setRedirect("/bloggers-page.htm");
					return false;
				}
			});

		} else {
			TextField username = new TextField(USERNAME_ATTRIBUTE);
			username.setLabel("Логин");
			username.setMinLength(1);
			username.setMaxLength(50);
			if (getContext().getSessionAttribute(USERNAME_ATTRIBUTE) != null) {
				username.setValue((String) getContext().getSessionAttribute(
						USERNAME_ATTRIBUTE));

			}
			getContext().setSessionAttribute(USERNAME_ATTRIBUTE, null);
			loginForm.add(username);

			PasswordField password = new PasswordField("password");
			password.setMinLength(1);
			password.setMaxLength(50);
			password.setLabel("Пароль");
			loginForm.add(password);

			Submit submitEnter = new Submit("Войти");
			loginForm.add(submitEnter);
			submitEnter.setActionListener(new ActionListener() {

				public boolean onAction(Control source) {
					if (loginForm.isValid()) {
						validateUser();
					}
					setRedirect("/bloggers-page.htm");
					return false;
				}
			});
		}
		return loginForm;
	}

	private String createLoginMessage() {
		String username = (String) getContext().getSessionAttribute(
				USERNAME_ATTRIBUTE);
		if (username != null) {
			if (getContext().getSessionAttribute(ROLE_ATTRIBUTE) != null) {
				loginMessage = "Welcome, " + username;
				loginMessage += ". Your role is "
						+ getContext().getSessionAttribute(ROLE_ATTRIBUTE);
			} else {
				loginMessage = "Invalid login or password";
			}
		}
		return loginMessage;
	}

	private boolean validateUser() {
		String username = loginForm.getFieldValue(USERNAME_ATTRIBUTE);
		String password = loginForm.getFieldValue("password");
		getContext().setSessionAttribute(USERNAME_ATTRIBUTE, username);
		if (checkUsernameAndPassword(username, password)) {
			getContext().setSessionAttribute(ROLE_ATTRIBUTE, BLOGGER_ROLE);
			return true;
		} else {
			return false;
		}
	}

	private void ivalidateUser() {
		if (getContext().getSession() != null) {
			getContext().getSession().invalidate();
		}

	}

	private boolean checkUsernameAndPassword(String username, String password) {
		if (username == null || password == null)
			return false;
		return username.equals("user") && password.equals("12345");
	}

	/**
	 * 
	 */
	public BorderedPage() {
		addModel("loginMessage", createLoginMessage());
		addControl(createLoginForm());
		addControl(createRegisterButton());
		addControl(createSearchForm());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.click.Page#getTemplate()
	 */
	@Override
	public String getTemplate() {
		return "/border.htm";
	}

}
