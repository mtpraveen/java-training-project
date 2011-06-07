package voting.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Vote operations and data storrage </p>
 * 
 * @author Alexander Nikoniuk
 */

@Entity
@Table(name = "votes")
@Configurable
public class Vote {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date voteDate = new Date();

    @ManyToOne
    private Account account;

    @ManyToOne
    private Question question;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<Answer>();    
    
    // ================JavaBean =============================
    
    public Date getVoteDate() {
        return this.voteDate;
    }
    
    public void setVoteDate(Date voteDate) {
        this.voteDate = voteDate;
    }
    
    public Account getAccount() {
        return this.account;
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }
    
    public Set<Answer> getAnswers() {
        return this.answers;
    }
    
    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account: ").append(getAccount()).append(", ");
        sb.append("Answers: ").append(getAnswers() == null ? "null" : getAnswers().size()).append(", ");
        sb.append("Question: ").append(getQuestion()).append(", ");
        sb.append("VoteDate: ").append(getVoteDate());
        return sb.toString();
    }
    
    // ================Entity ===============================
    
    @PersistenceContext
    transient EntityManager entityManager;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Integer version;
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getVersion() {
        return this.version;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Vote attached = Vote.findVote(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Vote merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Vote merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager entityManager() {
        EntityManager em = new Vote().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long countVotes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Vote o", Long.class).getSingleResult();
    }
       
    public static long countAnswersBetweenDates(Answer answer, Date startDate, Date endDate) {
    	if (answer == null) throw new IllegalArgumentException("The answer argument is required");
    	if (startDate == null) throw new IllegalArgumentException("The startDate argument is required");
    	if (endDate == null) throw new IllegalArgumentException("The endDate argument is required");

    	TypedQuery<Long> q = entityManager().createQuery("SELECT COUNT(o) FROM Vote o WHERE o.voteDate BETWEEN :startDate AND :endDate " +
    														" AND EXISTS (SELECT p FROM o.answers p WHERE p = :answer)", Long.class);
    	q.setParameter("answer", answer);
    	q.setParameter("startDate", startDate);
    	q.setParameter("endDate", endDate);
        return q.getSingleResult();
    } 
    
	public static long countQuestionVotesByUser(Question question, Account account) {
    	if (question == null) throw new IllegalArgumentException("The question argument is required");
    	if (account == null) throw new IllegalArgumentException("The account argument is required");
		
    	TypedQuery<Long> q = entityManager().createQuery("SELECT COUNT(o) FROM Vote o WHERE o.question = :question " +
															" AND o.account = :account", Long.class);
    	q.setParameter("question", question);
    	q.setParameter("account", account);
		return q.getSingleResult();
	}
    
    public static List<Vote> findAllVotes() {
        return entityManager().createQuery("SELECT o FROM Vote o", Vote.class).getResultList();
    }
    
    public static Vote findVote(Long id) {
        if (id == null) return null;
        return entityManager().find(Vote.class, id);
    }
    
    public static List<Vote> findVoteEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Vote o", Vote.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
