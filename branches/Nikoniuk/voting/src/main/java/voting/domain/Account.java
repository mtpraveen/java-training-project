package voting.domain;

import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(table = "accounts")
public class Account {

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 5, max = 25)
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
}
