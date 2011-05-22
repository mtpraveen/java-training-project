package voting.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import voting.domain.Category;

@RequestMapping("/")
@Controller
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	@ModelAttribute("categories")
	public java.util.Collection<Category> populateCategories() {
		return Category.findAllCategorys();
	}

}
