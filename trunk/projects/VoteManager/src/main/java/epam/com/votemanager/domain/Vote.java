package epam.com.votemanager.domain;

import java.sql.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@AttributeOverrides({
			@AttributeOverride(name = "contactId", column = @Column(name = "id")),
			@AttributeOverride(name = "variantId", column = @Column(name = "idVariant")),
			@AttributeOverride(name = "questionId", column = @Column(name = "idQuestion")) })
	private KeyForVote key;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Date")
	private Date date;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public KeyForVote getKey() {
		return key;
	}

	public void setKey(KeyForVote key) {
		this.key = key;
	}

}
