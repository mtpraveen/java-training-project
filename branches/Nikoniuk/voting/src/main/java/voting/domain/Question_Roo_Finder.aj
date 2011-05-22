// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Question_Roo_Finder {
    
    public static List<Question> Question.findQuestionsByAccount(Account account) {
        if (account == null) throw new IllegalArgumentException("The account argument is required");
        EntityManager em = Question.entityManager();
        TypedQuery<Question> q = em.createQuery("SELECT o FROM Question AS o WHERE o.account = :account", Question.class);
        q.setParameter("account", account);
        return q.getResultList();
    }
    
    public static List<Question> Question.findQuestionsByCategory(Category category) {
        if (category == null) throw new IllegalArgumentException("The category argument is required");
        EntityManager em = Question.entityManager();
        TypedQuery<Question> q = em.createQuery("SELECT o FROM Question AS o WHERE o.category = :category", Question.class);
        q.setParameter("category", category);
        return q.getResultList();
    }
    
}
