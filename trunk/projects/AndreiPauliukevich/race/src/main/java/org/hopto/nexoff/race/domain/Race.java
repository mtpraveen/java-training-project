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
@NamedQueries({ @NamedQuery(name = "Race.findAll", query = "select r from Race r"), 
				@NamedQuery(name = "Race.findByIdFetch", query = "select r from Race r left join fetch r.horses where r.id = :id ")
})
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coeff == null) ? 0 : coeff.hashCode());
		result = prime * result + ((horses == null) ? 0 : horses.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Race other = (Race) obj;
		if (coeff == null) {
			if (other.coeff != null)
				return false;
		} else if (!coeff.equals(other.coeff))
			return false;
		if (horses == null) {
			if (other.horses != null)
				return false;
		} else if (!horses.equals(other.horses))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}
	
	

}
