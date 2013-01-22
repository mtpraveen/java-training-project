package epam.com.votemanager.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GROUPS")
public class Group implements Serializable {

	private static final long serialVersionUID = 4860981009718520373L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GROUP_ID")
	private Integer id;

	@Column(name = "GROUP_NAME")
	private String groupName;

	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "GROUPS_ROLES", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;

	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "USERS_GROUPS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = { @JoinColumn(name = "ID") })
	private List<Contact> users;

	public String getGroupName() {
		return groupName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<Contact> getUsers() {
		return users;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsers(List<Contact> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}