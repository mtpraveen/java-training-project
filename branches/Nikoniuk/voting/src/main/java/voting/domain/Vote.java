package voting.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import voting.domain.Account;
import javax.persistence.ManyToOne;
import voting.domain.Question;
import java.util.Set;
import voting.domain.Answer;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@RooJavaBean
@RooToString
@RooEntity(table = "votes")
public class Vote {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date voteDate;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Question question;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Answer> answers = new HashSet<Answer>();
}
