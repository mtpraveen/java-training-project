// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import java.util.Set;

privileged aspect Question_Roo_JavaBean {
    
    public String Question.getName() {
        return this.name;
    }
    
    public void Question.setName(String name) {
        this.name = name;
    }
    
    public boolean Question.getMultipleChoice() {
        return this.multipleChoice;
    }
    
    public void Question.setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }
    
    public Boolean Question.getVisible() {
        return this.visible;
    }
    
    public void Question.setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    public Category Question.getCategory() {
        return this.category;
    }
    
    public void Question.setCategory(Category category) {
        this.category = category;
    }
    
    public Set<Vote> Question.getVotes() {
        return this.votes;
    }
    
    public void Question.setVotes(Set<Vote> votes) {
        this.votes = votes;
    }
    
    public java.util.Set<Answer> Question.getAnswers() {
        return this.answers;
    }
    
    public void Question.setAnswers(java.util.Set<Answer> answers) {
        this.answers = answers;
    }
    
    public java.util.Date Question.getCreationDate() {
        return this.creationDate;
    }
    
    public void Question.setCreationDate(java.util.Date creationDate) {
        this.creationDate = creationDate;
    }
    
}
