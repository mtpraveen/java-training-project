package voting.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Category operations and data storrage </p>
 * 
 * @author Alexander Nikoniuk
 */
@Entity
@Table(name = "categories")
@Configurable
public class Category {
	
	@NotNull
	private String name;

    @Size(max = 1000)
    private String text;

    private Boolean visible = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Question> questions = new HashSet<Question>();
    
    // ================JavaBean =============================
    
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public Boolean getVisible() {
        return this.visible;
    }
    
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Set<Question> getQuestions() {
        return this.questions;
    }
    
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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
            Category attached = Category.findCategory(this.id);
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
    public Category merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Category merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager entityManager() {
        EntityManager em = new Category().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long countCategories() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Category o", Long.class).getSingleResult();
    }
    
    public static long countQuestions(Category category) {
    	TypedQuery<Long> q = entityManager().createQuery("SELECT COUNT(o) FROM Question o WHERE o.category = :category AND o.visible = true", Long.class);
        q.setParameter("category", category);
        return q.getSingleResult();
    }
    
    public static List<Question> findQuestionEntries(Category category, int firstResult, int maxResults) {
    	TypedQuery<Question> q = entityManager().createQuery("SELECT o FROM Question o WHERE o.category = :category and o.visible = true", Question.class);
        q.setParameter("category", category);
        return q.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Category> findAllCategories() {
        return entityManager().createQuery("SELECT o FROM Category o WHERE o.visible = true order by o.name", Category.class).getResultList();
    }
    
    public static Category findCategory(Long id) {
        if (id == null) return null;
        return entityManager().find(Category.class, id);
    }
    
    public static List<Category> findCategoryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Category o", Category.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static Category findCategoryByName(String name) {
    	if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Category.entityManager();
        TypedQuery<Category> q = em.createQuery("SELECT o FROM Category AS o WHERE o.name = :name", Category.class);
        q.setParameter("name", name);
        Category result = null;
        try {
        	result = q.getSingleResult();
        }
    	finally {
    		return result;
    	}
    }
}
