package voting.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Answers operations and data storrage </p>
 * 
 * @author Alexander Nikoniuk
 */
@Entity
@Table(name = "answers")
@Configurable
public class Answer {

    @NotNull
    private String text;

    @ManyToOne
    private Question question;
    
    // ================JavaBean =============================
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Question getQuestion() {
        return this.question;
    }
    
    public void setQuestion(Question question) {
        this.question = question;
    }

    public String toString() {
        return getText();
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
        Query q = entityManager.createNativeQuery("delete from votes_answers where answers = " + this.getId());
        q.executeUpdate();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Answer attached = Answer.findAnswer(this.id);
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
    public Answer merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Answer merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager entityManager() {
        EntityManager em = new Answer().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
  	public static long countAnswers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Answer o", Long.class).getSingleResult();
    }
    
    public static List<Answer> findAllAnswers() {
        return entityManager().createQuery("SELECT o FROM Answer o", Answer.class).getResultList();
    }
    
    public static Answer findAnswer(Long id) {
        if (id == null) return null;
        return entityManager().find(Answer.class, id);
    }
    
    public static List<Answer> findAnswerEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Answer o", Answer.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
}
