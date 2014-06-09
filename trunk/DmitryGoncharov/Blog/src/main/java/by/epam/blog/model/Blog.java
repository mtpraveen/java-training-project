package by.epam.blog.model;

import java.io.Serializable;
import java.util.Set;
import java.util.SortedSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Id;

/**
 * @author Dmitry_Goncharov
 *
 */
@Entity
@Table(name = "blog")
public class Blog implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	
	@Column(name = "blog_name")
	private String name;
	
	@OneToOne(mappedBy = "blog")
	private User user;
	
	@OneToMany(mappedBy = "blog",fetch=FetchType.EAGER)
	@OrderBy("id")
	//@javax.persistence.OrderBy("caption, text")
	private Set <Topic> topics;

	public Blog(){
		super();
	}
	public Blog(String name){
		this.name=name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(SortedSet<Topic> topics) {
		this.topics = topics;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
