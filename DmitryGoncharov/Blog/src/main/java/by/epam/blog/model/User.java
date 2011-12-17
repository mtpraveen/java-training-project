/**
 * Trainig Sample project
 * Admin
 */
package by.epam.blog.model;

/**
 * @author Dmitry_Goncharov
 *
 */
public class User {
	private String login;
	private String pass;
	private String name;
	private int id;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
