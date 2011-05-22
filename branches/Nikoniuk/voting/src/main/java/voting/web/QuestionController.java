package voting.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import voting.domain.Category;
import voting.domain.Question;

@RooWebScaffold(path = "questions", formBackingObject = Question.class)
@RequestMapping("/questions")
@Controller
public class QuestionController {
    @ModelAttribute("categories")
    public java.util.Collection<Category> populateCategories() {
        return Category.findAllCategorys();
    }	
}
