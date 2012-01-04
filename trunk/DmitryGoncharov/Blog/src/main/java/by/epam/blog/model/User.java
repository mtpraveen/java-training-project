package by.epam.blog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dmitry_Goncharov
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "USER_LOGIN")
	private String login;
	@Column(name = "USER_PASS")
	private String pass;
	@Column(name = "USER_NAME")
	private String name;
/*
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private Collection<Blog> blogs = new LinkedHashSet<Blog>();
	
	public Collection<Blog> getBlogs() {
		return blogs;
	}
*/
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
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
