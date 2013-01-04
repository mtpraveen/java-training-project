package org.hopto.nexoff.race.domain;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
@NamedQueries({ @NamedQuery(name = "Race.findAll", query = "select r from Race r"), @NamedQuery(name = "Race.findByIdFetch", query = "select r from Race r left join fetch r.horses horse where r.id = :id ") })
public class Race implements Serializable {

	private static final long serialVersionUID = -3840969070771025931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "START_TIME")
	private Timestamp startTime;

	@Column(name = "COEFF")
	private Double coeff;

	@OneToOne(fetch = FetchType.EAGER)
	private Horse winner;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinTable(name = "RACE_MEMBERS", joinColumns = { @JoinColumn(name = "RACE_ID") }, inverseJoinColumns = { @JoinColumn(name = "HORCE_ID") })
	private List<Horse> horses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Double getCoeff() {
		return coeff;
	}

	public void setCoeff(Double coeff) {
		this.coeff = coeff;
	}

	public List<Horse> getHorses() {
		return horses;
	}

	public void setHorses(List<Horse> horses) {
		this.horses = horses;
	}

	public Horse getWinner() {
		return winner;
	}

	public void setWinner(Horse winner) {
		this.winner = winner;
	}

	@Override
	public String toString() {
		return "Race [id=" + id + ", startTime=" + startTime + ", coeff=" + coeff + "]";
	}

}
