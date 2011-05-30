package by.brsu.java.training.page;

import java.util.Date;

import org.apache.click.ActionListener;
import org.apache.click.Control;
import org.apache.click.control.Form;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Submit;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;

import by.brsu.java.training.entity.User;
import by.brsu.java.training.service.UserService;
import by.brsu.java.training.util.PasswordUtil;

public class RegistrationPage extends HomePage {
	private static final String REGISTER_MESSAGE_ATTRIBUTE = "registrationMessage";

	Form registrationForm;
	String registrationMessage = "";

	private Form createRegistrationForm() {
		registrationForm = new Form("registrationForm");
		createRegistrationFields(registrationForm);
		Submit submitRegister = new Submit("submitRegister");
		submitRegister.setLabel("Register");
		registrationForm.add(submitRegister);
		submitRegister.setActionListener(new ActionListener() {

			public boolean onAction(Control source) {
				if (registrationForm.isValid()) {
					String name = registrationForm.getFieldValue("nameField");
					String password = registrationForm
							.getFieldValue("passwordField");
					String confirmPassword = registrationForm
							.getFieldValue("confirmPasswordField");
					String about = registrationForm.getFieldValue("aboutField");
					if (UserService.getInstance().isUserExists(name)) {
						getContext().setRequestAttribute(
								REGISTER_MESSAGE_ATTRIBUTE,
								"User with this nickname is already exists");
						setForward("/registration-page.htm");
					} else if (!password.equals(confirmPassword)) {
						getContext().setRequestAttribute(
								REGISTER_MESSAGE_ATTRIBUTE,
								"The entered passwords are not equal");
						setForward("/registration-page.htm");

					} else {
						invalidateUser();
						User user = new User(0, name, PasswordUtil.hashString(password), new Date(),
								about, null, false, false);
						UserService.getInstance().addUser(user);
						user = UserService.getInstance().getUserByName(name);
						getContext().setSessionAttribute(ID_ATTRIBUTE,
								user.getId());
						getContext().setSessionAttribute(USERNAME_ATTRIBUTE,
								name);
						if (user.isModerator()) {
							getContext().setSessionAttribute(ROLE_ATTRIBUTE,
									UserRole.MODERATOR);
						} else {
							getContext().setSessionAttribute(ROLE_ATTRIBUTE,
									UserRole.BLOGGER);
						}
						setRedirect("/bloggers-page.htm");
					}
				}
				return false;
			}
		});

		return registrationForm;

	}

	private void createRegistrationFields(Form registrationForm) {
		TextField nameField = new TextField("nameField", true);
		nameField.setLabel("Nickname");
		nameField.setMinLength(1);
		nameField.setMaxLength(50);
		nameField.setSize(20);
		String nameString = getContext().getRequestParameter("nameField");
		if (nameString != null) {
			nameField.setValue(nameString);
		}
		registrationForm.add(nameField);

		PasswordField passwordField = new PasswordField("passwordField", true);
		passwordField.setLabel("Password");
		passwordField.setMinLength(4);
		passwordField.setMaxLength(50);
		passwordField.setSize(20);
		String passwordString = getContext().getRequestParameter(
				"passwordField");
		if (passwordString != null) {
			passwordField.setValue(passwordString);
		}
		registrationForm.add(passwordField);

		PasswordField confirmPasswordField = new PasswordField(
				"confirmPasswordField", true);
		confirmPasswordField.setLabel("Confirm password");
		confirmPasswordField.setMinLength(4);
		confirmPasswordField.setMaxLength(50);
		confirmPasswordField.setSize(20);
		String confirmPasswordString = getContext().getRequestParameter(
				"confirmPasswordField");
		if (confirmPasswordString != null) {
			confirmPasswordField.setValue(confirmPasswordString);
		}
		registrationForm.add(confirmPasswordField);

		TextArea aboutField = new TextArea("aboutField");
		aboutField.setLabel("About yourself");
		aboutField.setMaxLength(5000);
		aboutField.setCols(50);
		aboutField.setRows(10);
		String aboutString = getContext().getRequestParameter("aboutField");
		if (aboutString != null) {
			aboutField.setValue(aboutString);
		}
		registrationForm.add(aboutField);
	}

	private String createRegistrationMessage() {
		String message = (String) getContext().getRequestAttribute(
				REGISTER_MESSAGE_ATTRIBUTE);
		if (message != null) {
			registrationMessage = message;
		}
		return registrationMessage;
	}

	public RegistrationPage() {
		addControl(createRegistrationForm());
		addModel("registrationMessage", createRegistrationMessage());
	}
}