package voting.web;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import voting.domain.Category;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "index";
	}

    @ModelAttribute("categories_menu")
    public java.util.Map<Category, Long> populateCategories() {
    	LinkedHashMap<Category, Long> categories = new LinkedHashMap<Category, Long>();    	
    	for (Category category: Category.findAllCategories()) {
    		categories.put(category, Category.countQuestions(category));
    	}
        return categories;
    }

}
