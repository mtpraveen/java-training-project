package org.hopto.nexoff.race.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "select u from User u"), 
				@NamedQuery(name = "User.findByUsernameWithAuthrities", query = "select u from User u left join fetch u.authorities where u.username = :username"),
				@NamedQuery(name = "User.isUniqueUsername", query = "select count(u.username) from User u where u.username = :username")
})
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = -718373281831262405L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "FIO")
	private String fio;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "MONEY")
	private double money;

	@ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.REFRESH)
	@JoinTable(name = "USER_AUTHORITIES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "AUTHORITY_ID") })
	private List<Authority> authorities;
	
	@ManyToOne
	private Bid bid;

	@Column(name = "ENABLED")
	private boolean enabled = true;

	@Column(name = "CREDENTIALSNONEXPIRED")
	private boolean credentialsNonExpired = true;

	@Column(name = "ACCOUNTNONLOCKED")
	private boolean accountNonLocked = true;

	@Column(name = "ACCOUNTNONEXPIRED")
	private boolean accountNonExpired = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}
	
	

}
