// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package voting.domain;

import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;
import voting.domain.Category;

privileged aspect CategoryDataOnDemand_Roo_DataOnDemand {
    
    declare @type: CategoryDataOnDemand: @Component;
    
    private Random CategoryDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Category> CategoryDataOnDemand.data;
    
    public Category CategoryDataOnDemand.getNewTransientCategory(int index) {
        voting.domain.Category obj = new voting.domain.Category();
        setText(obj, index);
        setVisible(obj, index);
        setName(obj, index);
        return obj;
    }
    
    private void CategoryDataOnDemand.setText(Category obj, int index) {
        java.lang.String text = "text_" + index;
        if (text.length() > 200) {
            text = text.substring(0, 200);
        }
        obj.setText(text);
    }
    
    private void CategoryDataOnDemand.setVisible(Category obj, int index) {
        java.lang.Boolean visible = true;
        obj.setVisible(visible);
    }
    
    private void CategoryDataOnDemand.setName(Category obj, int index) {
        java.lang.String name = "name_" + index;
        obj.setName(name);
    }
    
    public Category CategoryDataOnDemand.getSpecificCategory(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Category obj = data.get(index);
        return Category.findCategory(obj.getId());
    }
    
    public Category CategoryDataOnDemand.getRandomCategory() {
        init();
        Category obj = data.get(rnd.nextInt(data.size()));
        return Category.findCategory(obj.getId());
    }
    
    public boolean CategoryDataOnDemand.modifyCategory(Category obj) {
        return false;
    }
    
    public void CategoryDataOnDemand.init() {
        data = voting.domain.Category.findCategoryEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Category' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<voting.domain.Category>();
        for (int i = 0; i < 10; i++) {
            voting.domain.Category obj = getNewTransientCategory(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}