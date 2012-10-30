// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voting.domain.CategoryDataOnDemand;
import voting.domain.Question;

privileged aspect QuestionDataOnDemand_Roo_DataOnDemand {
    
    declare @type: QuestionDataOnDemand: @Component;
    
    private Random QuestionDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Question> QuestionDataOnDemand.data;
    
    @Autowired
    private CategoryDataOnDemand QuestionDataOnDemand.categoryDataOnDemand;
    
    public Question QuestionDataOnDemand.getNewTransientQuestion(int index) {
        voting.domain.Question obj = new voting.domain.Question();
        setName(obj, index);
        setMultipleChoice(obj, index);
        setVisible(obj, index);
        setCategory(obj, index);
        setCreationDate(obj, index);
        return obj;
    }
    
    private void QuestionDataOnDemand.setName(Question obj, int index) {
        java.lang.String name = "name_" + index;
        if (name.length() > 150) {
            name = name.substring(0, 150);
        }
        obj.setName(name);
    }
    
    private void QuestionDataOnDemand.setMultipleChoice(Question obj, int index) {
        java.lang.Boolean multipleChoice = true;
        obj.setVisible(multipleChoice);
    }
    
    private void QuestionDataOnDemand.setVisible(Question obj, int index) {
        java.lang.Boolean visible = true;
        obj.setVisible(visible);
    }
    
    private void QuestionDataOnDemand.setCategory(Question obj, int index) {
        voting.domain.Category category = categoryDataOnDemand.getRandomCategory();
        obj.setCategory(category);
    }

    private void QuestionDataOnDemand.setCreationDate(Question obj, int index) {
        java.util.Date creationDate = new java.util.GregorianCalendar(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR), java.util.Calendar.getInstance().get(java.util.Calendar.MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY), java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE), java.util.Calendar.getInstance().get(java.util.Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationDate(creationDate);
    }
    
    public Question QuestionDataOnDemand.getSpecificQuestion(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Question obj = data.get(index);
        return Question.findQuestion(obj.getId());
    }
    
    public Question QuestionDataOnDemand.getRandomQuestion() {
        init();
        Question obj = data.get(rnd.nextInt(data.size()));
        return Question.findQuestion(obj.getId());
    }
    
    public boolean QuestionDataOnDemand.modifyQuestion(Question obj) {
        return false;
    }
    
    public void QuestionDataOnDemand.init() {
        data = voting.domain.Question.findQuestionEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Question' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<voting.domain.Question>();
        for (int i = 0; i < 10; i++) {
            voting.domain.Question obj = getNewTransientQuestion(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}