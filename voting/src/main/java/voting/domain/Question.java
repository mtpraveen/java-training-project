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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Question operations and data storrage </p>
 * 
 * @author Alexander Nikoniuk
 */
@Entity
@Table(name = "questions")
@Configurable
public class Question {

    @NotNull
    @Size(max = 100)
    private String name;

    private boolean multipleChoice = false;

    private boolean visible = true;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date creationDate = new Date();

    @ManyToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Vote> votes = new HashSet<Vote>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Answer> answers = new HashSet<Answer>();
    
    // ================JavaBean =============================
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public boolean getMultipleChoice() {
        return this.multipleChoice;
    }
    
    public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }
    
    public Boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
    
    public Set<Vote> getVotes() {
        return this.votes;
    }
    
    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
    
    public java.util.Set<Answer> getAnswers() {
        return this.answers;
    }
    
    public void setAnswers(java.util.Set<Answer> answers) {
        this.answers = answers;
    }
    
    public java.util.Date getCreationDate() {
        return this.creationDate;
    }
    
    public void setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
    
    public String toString() {
        return getName();
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
            Question attached = Question.findQuestion(this.id);
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
    public Question merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Question merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager entityManager() {
        EntityManager em = new Question().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long countQuestions() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Question o", Long.class).getSingleResult();
    }
    
    public static List<Question> findAllQuestions() {
        return entityManager().createQuery("SELECT o FROM Question o", Question.class).getResultList();
    }
    
    public static Question findQuestion(Long id) {
        if (id == null) return null;
        return entityManager().find(Question.class, id);
    }
    
    public static List<Question> findQuestionEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Question o", Question.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
}
