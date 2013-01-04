package org.hopto.nexoff.race.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Authority.findAll", query = "select a from Authority a"), 
				@NamedQuery(name = "Authority.findByAuthority", query = "select a from Authority a where authority = :authority") 
})
public class Authority implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = -9207898797887334143L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column
	private String authority;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return "Authority [id=" + id + ", authority=" + authority + "]";
	}
	
	
}
