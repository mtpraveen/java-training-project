package epam.com.votemanager.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class KeyForVote implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer contactId;
	private Integer variantId;
	private Integer questionId;

	public KeyForVote(Integer contactId, Integer variantId, Integer questionId) {
		this.contactId = contactId;
		this.variantId = variantId;
		this.questionId = questionId;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Integer getVariantId() {
		return variantId;
	}

	public void setVariantId(Integer variantId) {
		this.variantId = variantId;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

}
