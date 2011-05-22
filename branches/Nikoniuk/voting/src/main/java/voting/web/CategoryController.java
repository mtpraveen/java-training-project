package voting.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import voting.domain.Category;

@RooWebScaffold(path = "categorys", formBackingObject = Category.class)
@RequestMapping("/categorys")
@Controller
public class CategoryController {
    @ModelAttribute("categories")
    public java.util.Collection<Category> populateCategories() {
        return Category.findAllCategorys();
    }
}
