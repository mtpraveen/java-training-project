// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import java.lang.String;

privileged aspect Answer_Roo_ToString {
    
    public String Answer.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question: ").append(getQuestion()).append(", ");
        sb.append("Text: ").append(getText());
        return sb.toString();
    }
    
}
