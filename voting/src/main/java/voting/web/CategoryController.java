package voting.web;

import static voting.security.VotingUserDetailsService.getCurrentAccount;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import voting.domain.Account;
import voting.domain.Category;
import voting.domain.Question;
import voting.domain.Vote;

/**
 * <p>Category controller(controller for operations with categories) </p>
 * 
 * @author Alexander Nikoniuk
 */
@Controller
public class CategoryController {

	private static final  Logger LOGGER = Logger.getLogger(CategoryController.class);
	
    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public String create(@Valid Category category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to create new category...");
    	if (Category.findCategoryByName(category.getName()) != null) {
        	bindingResult.rejectValue("name", "error_category_exists");
        }

    	if (bindingResult.hasErrors()) {
            uiModel.addAttribute("category", category);
            LOGGER.warn(getCurrentAccount(httpServletRequest) + ": wrong category data");
            return "categories/create";
        }
        uiModel.asMap().clear();
        category.persist();
        LOGGER.info("new category " + category.getName() + " was successfully created");
        return "redirect:/category/" + category.getId();
    }
    
    @RequestMapping(value = "/categories", params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel) {
        uiModel.addAttribute("category", new Category());
        return "categories/create";
    }
    
    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, HttpServletRequest httpServletRequest) {
    	Category category = Category.findCategory(id);
		uiModel.addAttribute("category", category);
		List<Question> questions = new ArrayList<Question>();
    	if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            questions = Category.findQuestionEntries(category, page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo);
            float nrOfPages = (float) Category.countQuestions(category) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
        	questions = new ArrayList<Question>(category.getQuestions());
        }
    	Account account = null;
    	Principal principal = httpServletRequest.getUserPrincipal();
    	if (principal != null)
    		account = Account.findAccountByName(principal.getName());
    	for (Question question: questions) {
    		if (account != null && Vote.countQuestionVotesByUser(question, account) > 0) 
    			question.setVisible(false);
    	}
    	uiModel.addAttribute("questions", questions);
        return "categories/show";
    }
    
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("categories", Category.findCategoryEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Category.countCategories() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("categories", Category.findAllCategories());
        }
        return "categories/list";
    }
    
    @RequestMapping(value = "/categories", method = RequestMethod.PUT)
    public String update(@Valid Category category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
    	Category oldCategory = Category.findCategory(category.getId());
    	LOGGER.info(getCurrentAccount(httpServletRequest) + " tries to update category " + oldCategory.getName() + "...");

    	//if name was changed, but such category name already exists in database
    	if (!category.getName().equalsIgnoreCase(oldCategory.getName()) &&
    			Category.findCategoryByName(category.getName()) != null) {
        	bindingResult.rejectValue("name", "error_category_exists");
        }
    	if (bindingResult.hasErrors()) {
            uiModel.addAttribute("category", category);
			LOGGER.warn(getCurrentAccount(httpServletRequest) + ": wrong update data for category "+ oldCategory.getName());
            return "categories/update";
        }
        uiModel.asMap().clear();
        category.merge();
        LOGGER.info(getCurrentAccount(httpServletRequest) + ": category " + oldCategory.getName() + " was successfully updated");
        return "redirect:/category/" + category.getId();
    }
    
    @RequestMapping(value = "/categories/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("category", Category.findCategory(id));
        return "categories/update";
    }
    
    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel, HttpServletRequest httpServletRequest) {
    	Category category = Category.findCategory(id);
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": tries to remove category " + category.getName() + "...");
		category.remove();
		LOGGER.info(getCurrentAccount(httpServletRequest) + ": category " + category.getName() + " was successfully removed ");
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/categories";
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
