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
import org.apache.click.extras.control.MenuFactory;

import by.brsu.java.training.entity.User;
import by.brsu.java.training.service.UserService;
import by.brsu.java.training.util.PasswordUtil;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class BorderedPage extends Page {
	public static final String ID_ATTRIBUTE = "userId";
	public static final String USERNAME_ATTRIBUTE = "username";
	public static final String ROLE_ATTRIBUTE = "role";
	public static final UserRole BLOGGER_ROLE = UserRole.BLOGGER;
	public static final UserRole MODERATOR_ROLE = UserRole.MODERATOR;
	public static final UserRole GUEST_ROLE = UserRole.GUEST;
	public static final String LOGIN_MESSAGE_ATTRIBUTE = "loginMessage";
	public static final String SEARCH_FIELD_WIDTH = "200";
	public static final String SEARCH_BUTTON_WIDTH = "120";
	public static final String LOGIN_FIELD_WIDTH = "166";
	

	private Form loginForm;
	private String loginMessage = "";

	private Form searchForm;

	protected boolean isUserAutorized() {
		return getUserRole() == BLOGGER_ROLE || getUserRole() == MODERATOR_ROLE;
	}

	protected UserRole getUserRole() {
		UserRole role;
		if (getContext().getSessionAttribute(ROLE_ATTRIBUTE) == null) {
			return UserRole.GUEST;
		}
		role = (UserRole) getContext().getSessionAttribute(ROLE_ATTRIBUTE);
		return role;
	}

	private Form createSearchForm() {
		searchForm = new Form("searchForm");
		TextField text = new TextField("text");
		text.setLabel("Text");
		text.setMaxLength(250);
		text.setWidth(SEARCH_FIELD_WIDTH);
		searchForm.add(text);

		TextField tags = new TextField("tags");
		tags.setLabel("Tags");
		tags.setMaxLength(250);
		tags.setWidth(SEARCH_FIELD_WIDTH);
		searchForm.add(tags);

		Submit submitSearchByText = new Submit("submitSearchByText");
		submitSearchByText.setLabel("Search by text");
		submitSearchByText.setWidth(SEARCH_BUTTON_WIDTH);
		searchForm.add(submitSearchByText);
		submitSearchByText.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		Submit submitSearchByTags = new Submit("submitSearchByTags");
		submitSearchByTags.setLabel("Search by tags");
		submitSearchByTags.setWidth(SEARCH_BUTTON_WIDTH);
		searchForm.add(submitSearchByTags);
		submitSearchByTags.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		return searchForm;
	}

	private Form createLoginForm() {
		loginForm = new Form("loginForm");
		if (isUserAutorized()) {
			return loginForm;
		}
		TextField usernameField = new TextField(USERNAME_ATTRIBUTE);
		usernameField.setLabel("Login");
		usernameField.setMinLength(1);
		usernameField.setMaxLength(50);
		usernameField.setWidth(LOGIN_FIELD_WIDTH);
		String usernameString = getContext().getRequestParameter("username");
		if (usernameString != null) {
			usernameField.setValue(usernameString);
			usernameField.setFocus(true);
		}
		loginForm.add(usernameField);

		PasswordField passwordField = new PasswordField("password");
		passwordField.setMinLength(1);
		passwordField.setMaxLength(50);
		passwordField.setLabel("Password");
		passwordField.setWidth(LOGIN_FIELD_WIDTH);
		loginForm.add(passwordField);

		Submit submitEnter = new Submit("Log in");
		submitEnter.setWidth(SEARCH_BUTTON_WIDTH);
		loginForm.add(submitEnter);
		submitEnter.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (loginForm.isValid()) {
					validateUser();
				}
				setForward("/bloggers-page.htm");
				return false;
			}
		});

		Submit submitRegister = new Submit("Register");
		submitRegister.setWidth(SEARCH_BUTTON_WIDTH);
		loginForm.add(submitRegister);
		submitRegister.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				setRedirect("/registration-page.htm");
				return false;
			}
		});
		return loginForm;
	}

	private String createLoginMessage() {
		if (getContext().getSessionAttribute(ROLE_ATTRIBUTE) != null) {
			String username = (String) getContext().getSessionAttribute(
					USERNAME_ATTRIBUTE);
			if (username != null) {
				loginMessage = "Welcome, " + username + "!";
				return loginMessage;
			}
		}

		String message = (String) getContext().getRequestAttribute(
				LOGIN_MESSAGE_ATTRIBUTE);
		if (message != null) {
			loginMessage = message;
		}
		return loginMessage;

	}

	private boolean validateUser() {
		String username = loginForm.getFieldValue("username");
		String password = loginForm.getFieldValue("password");
		password = PasswordUtil.hashString(password);
		User user = UserService.getInstance().getUserByName(username);
		getContext().setSessionAttribute(USERNAME_ATTRIBUTE, username);
		if (user == null) {
			getContext().setRequestAttribute(LOGIN_MESSAGE_ATTRIBUTE,
					"Invalid login or password");
			return false;
		}
		if (user.isBanned()) {
			getContext().setRequestAttribute(LOGIN_MESSAGE_ATTRIBUTE,
					"User " + username + " is banned");
			return false;
		}
		if (!password.equals(user.getPassword())) {
			getContext().setRequestAttribute(LOGIN_MESSAGE_ATTRIBUTE,
					"Invalid login or password");
			return false;
		} else {
			if (user.isModerator()) {
				getContext().setSessionAttribute(ROLE_ATTRIBUTE,
						UserRole.MODERATOR);
			} else {
				getContext().setSessionAttribute(ROLE_ATTRIBUTE,
						UserRole.BLOGGER);
			}
			getContext().setSessionAttribute(ID_ATTRIBUTE, user.getId());
			return true;
		}
	}

	protected void invalidateUser() {
		if (getContext().getSession() != null) {
			getContext().getSession().invalidate();
		}
	}

	/**
	 * 
	 */
	public BorderedPage() {
		createLoginMessage();
		if ( !isUserAutorized() ) {
			addModel("warningMessage", loginMessage);
		} else {
			addModel("loginMessage",  loginMessage);
		}

		addControl(createLoginForm());

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
