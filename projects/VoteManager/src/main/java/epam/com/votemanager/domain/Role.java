package epam.com.votemanager.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = -3610956691587149069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID")
	private Long id;

	@Column(name = "ROLE_NAME")
	private String authority;

	@ManyToMany
	@JoinTable(name = "GROUPS_ROLES", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
	private List<Group> groups;

	public Long getId() {
		return id;
	}

	public String getAuthority() {
		return authority;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

}