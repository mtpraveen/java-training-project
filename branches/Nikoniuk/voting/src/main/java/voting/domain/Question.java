package voting.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(table = "questions")
public class Question {

    @NotNull
    @Size(max = 150)
    private String name;

    private boolean multipleChoice = false;

    private Boolean visible = true;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Account account;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Vote> votes = new HashSet<Vote>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Answer> answers = new HashSet<Answer>();
}
