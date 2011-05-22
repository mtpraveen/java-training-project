package voting.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import voting.domain.Account;
import voting.domain.Category;

@RooWebScaffold(path = "accounts", formBackingObject = Account.class)
@RequestMapping("/accounts")
@Controller
public class AccountController {
    @ModelAttribute("categories")
    public java.util.Collection<Category> populateCategories() {
        return Category.findAllCategorys();
    }
	
}
