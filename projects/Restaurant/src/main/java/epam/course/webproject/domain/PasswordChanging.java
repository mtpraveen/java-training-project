package epam.course.webproject.domain;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class PasswordChanging {

	private String oldPassword;
	private String newPassword;

	public PasswordChanging() {
		
	}
	
	public PasswordChanging(String oldPassword, String newPassword) {
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	@NotEmpty(message="{validation.password.NotEmpty.message}")
	@Size(min=6, max=250, message="{validation.password.Size.message}")
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	@NotEmpty(message="{validation.password.NotEmpty.message}")
	@Size(min=6, max=250, message="{validation.password.Size.message}")
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
