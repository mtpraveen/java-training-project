// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Transient;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect Account_Roo_JavaBean {
	
	@Transient
	@Autowired
	private BasicTextEncryptor Account.textEncryptor;
	  
    public String Account.getName() {
        return this.name;
    }
    
    public void Account.setName(String name) {
        this.name = name;
    }
    
    public String Account.getPassword() {
        return textEncryptor.decrypt(this.password);
    }
    
    public void Account.setPassword(String password) {
        this.password = textEncryptor.encrypt(password);
    }
    
    public String Account.getConfirmPassword() {
        return this.confirmPassword;
    }
    
    public void Account.setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    public String Account.getEmail() {
        return this.email;
    }
    
    public void Account.setEmail(String email) {
        this.email = email;
    }
    
    public SystemRole Account.getSystemRole() {
        return this.systemRole;
    }
    
    public void Account.setSystemRole(SystemRole systemRole) {
        this.systemRole = systemRole;
    }
    
    public Boolean Account.getBanned() {
        return this.banned;
    }
    
    public void Account.setBanned(Boolean banned) {
        this.banned = banned;
    }
    
    public Date Account.getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void Account.setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Gender Account.getGender() {
        return this.gender;
    }
    
    public void Account.setGender(Gender gender) {
        this.gender = gender;
    }
    
    public String Account.getCaptchaText() {
        return this.captchaText;
    }
    
    public void Account.setCaptchaText(String text) {
        this.captchaText = text;
    }
    
    public Set<Vote> Account.getVotes() {
        return this.votes;
    }
    
    public void Account.setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
    
}
