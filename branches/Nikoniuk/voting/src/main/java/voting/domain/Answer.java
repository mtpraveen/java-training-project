package voting.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import voting.domain.Question;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@RooJavaBean
@RooToString
@RooEntity(table = "answers")
public class Answer {

    @NotNull
    private String text;

    @ManyToOne
    private Question question;
    
    @Transient
    private boolean checked = false;    
    
}
