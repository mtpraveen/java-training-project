package voting.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Account operations and data storrage </p>
 * 
 * @author Alexander Nikoniuk
 */

@Entity
@Table(name = "accounts")
@Configurable
public class Account {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Size(min = 5, max = 150)
    private String password;

    @Transient
    private String confirmPassword;
    
    @Transient
    private String captchaText;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Enumerated
    private SystemRole systemRole = SystemRole.USER;

    private Boolean banned = false;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(style = "S-")
    private Date dateOfBirth;

    @Enumerated
    private Gender gender;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
    private Set<Vote> votes = new HashSet<Vote>();
    
    // ================JavaBean =============================
    
	@Transient
	@Autowired
	private BasicTextEncryptor textEncryptor;
	  
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return textEncryptor.decrypt(this.password);
    }
    
    public void setPassword(String password) {
        this.password = textEncryptor.encrypt(password);
    }
    
    public String getConfirmPassword() {
        return this.confirmPassword;
    }
    
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public SystemRole getSystemRole() {
        return this.systemRole;
    }
    
    public void setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }
    
    public Boolean getBanned() {
        return this.banned;
    }
    
    public void setBanned(Boolean banned) {
        this.banned = banned;
    }
    
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Gender getGender() {
        return this.gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String getCaptchaText() {
        return this.captchaText;
    }
    
    public void setCaptchaText(String text) {
        this.captchaText = text;
    }
    
    public Set<Vote> getVotes() {
        return this.votes;
    }
    
    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
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
            Account attached = Account.findAccount(this.id);
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
    public Account merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Account merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public static final EntityManager entityManager() {
        EntityManager em = new Account().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long countAccounts() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Account o", Long.class).getSingleResult();
    }
    
    public static List<Account> findAllAccounts() {
        return entityManager().createQuery("SELECT o FROM Account o", Account.class).getResultList();
    }
    
    public static Account findAccount(Long id) {
        if (id == null) return null;
        return entityManager().find(Account.class, id);
    }
    
    public static List<Account> findAccountEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Account o", Account.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static Account findAccountByName(String name) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        EntityManager em = Account.entityManager();
        TypedQuery<Account> q = em.createQuery("SELECT o FROM Account AS o WHERE o.name = :name", Account.class);
        q.setParameter("name", name);
        Account result = null;
        try {
        	result = q.getSingleResult();
        }
    	finally {
    		return result;
    	}
    }
    
    public static Account findAccountByEmail(String email) {
        if (email == null || email.length() == 0) throw new IllegalArgumentException("The email argument is required");
        EntityManager em = Account.entityManager();
        TypedQuery<Account> q = em.createQuery("SELECT o FROM Account AS o WHERE o.email = :email", Account.class);
        q.setParameter("email", email);
        Account result = null;
        try {
        	result = q.getSingleResult();
        }
    	finally {
    		return result;
    	}
    }
        
}
