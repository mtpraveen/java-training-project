package voting.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.Size;
import java.util.Set;
import voting.domain.Question;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooEntity(table = "categories")
public class Category {
	
	@NotNull
	private String name;

    @Size(max = 1000)
    private String text;

    private Boolean visible = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Question> questions = new HashSet<Question>();
}
