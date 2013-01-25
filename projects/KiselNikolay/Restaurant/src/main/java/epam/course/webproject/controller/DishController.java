package epam.course.webproject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import epam.course.webproject.domain.Dish;
import epam.course.webproject.domain.RestaurantMenu;
import epam.course.webproject.domain.SearchCriteria;
import epam.course.webproject.domain.Section;
import epam.course.webproject.form.Message;
import epam.course.webproject.service.DishService;
import epam.course.webproject.service.DishesService;
import epam.course.webproject.service.SectionService;

@RequestMapping("/dishes")
@Controller
public class DishController {

	final Logger logger = LoggerFactory.getLogger(DishController.class);

	@Autowired
	private DishService dishService;

	@Autowired
	private SectionService sectionService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String showMenu(Model uiModel) {
		createModelForDishList(uiModel);
		SearchCriteria criteria = new SearchCriteria();
		criteria.setMaxPrice(10000);
		uiModel.addAttribute("searchCriteria", criteria);
		return "dishes/list";

	}

//	@RequestMapping(value = "/menu", method = RequestMethod.POST)
//	public String findDishes(@Valid SearchCriteria criteria,
//			BindingResult bindingResult, Model uiModel) {
//		if (bindingResult.hasErrors()) {
//			createModelForDishList(uiModel);
//			return "dishes/list";
//		}
//		RestaurantMenu rmenu = new RestaurantMenu();
//		List<Dish> dishes = null;
//		if (criteria.getSection().getNameSection().equalsIgnoreCase("All")) {
//			dishes = dishService.findByPrice(criteria.getMinPrice(),
//					criteria.getMaxPrice());
//		} else
//			dishes = dishService.findBySectionAndPrice(criteria.getSection(),
//					criteria.getMinPrice(), criteria.getMaxPrice());
//		rmenu.setDishes(DishesService.findByName(dishes, criteria.getDishName()));
//		rmenu.sort();
//		List<Section> sections = sectionService.findAll();
//		uiModel.addAttribute("rmenu", rmenu);
//		uiModel.addAttribute("sections", sections);
//		uiModel.addAttribute("searchCriteria", criteria);
//		return "dishes/list";
//
//	}

	public void createModelForDishList(Model uiModel) {
		RestaurantMenu rmenu = new RestaurantMenu(dishService.findAll());
		rmenu.sort();
		List<Section> sections = sectionService.findAll();
		uiModel.addAttribute("rmenu", rmenu);
		uiModel.addAttribute("sections", sections);

	}

	@RequestMapping(value = "/{nameDish}", method = RequestMethod.GET)
	public String showDish(@PathVariable("nameDish") String nameDish,
			Model uiModel) {
		Dish dish = dishService.findByName(nameDish);
		uiModel.addAttribute("dish", dish);
		return "dishes/show";
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/edit/{nameDish}", method = RequestMethod.GET)
	public String editDishForm(@PathVariable("nameDish") String nameDish,
			Model uiModel) {
		Dish dish = dishService.findByName(nameDish);
		List<Section> sections = sectionService.findAll();
		uiModel.addAttribute("dish", dish);
		uiModel.addAttribute("sections", sections);
		return "dishes/edit";
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/edit/{nameDish}", params = "del", method = RequestMethod.GET)
	public String deleteDish(@PathVariable("nameDish") String nameDish,
			Model uiModel, Locale locale) {
		Dish dish = dishService.findByName(nameDish);
		dishService.remove(dish);
		uiModel.addAttribute(
				"message",
				new Message("success", messageSource.getMessage("dish_deleted",
						null, locale)));
		return showMenu(uiModel);
	}

	@RequestMapping(value = "/edit/{nameDish}", method = RequestMethod.POST)
	public String updateDish(@Valid Dish dish, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "file", required = false) Part file) {
		if (bindingResult.hasErrors()) {
			List<Section> sections = sectionService.findAll();
			uiModel.addAttribute("sections", sections);
			return "dishes/edit";
		}
		if (file.getSize()==0) {
			System.out.println("OK!!!!!");
			Dish oldDish = dishService.findByName(dish.getNameDish());
			dish.setImage(oldDish.getImage());
			dishService.save(dish);
		} else 
			saveDish(dish, file);
		uiModel.addAttribute(
				"message",
				new Message("success", messageSource.getMessage("dish_updated",
						null, locale)));
		return showMenu(uiModel);
	}

	@RequestMapping(value = "/image/{nameDish}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] downloadPhoto(@PathVariable("nameDish") String nameDish) {

		Dish dish = dishService.findByName(nameDish);

		return dish.getImage();
	}

	@PreAuthorize("hasRole('admin')")
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addDishForm(Model uiModel) {
		Dish dish = new Dish();
		List<Section> sections = sectionService.findAll();
		uiModel.addAttribute("dish", dish);
		uiModel.addAttribute("sections", sections);
		return "dishes/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addDish(@Valid Dish dish, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale,
			@RequestParam(value = "file", required = false) Part file) {
		if (bindingResult.hasErrors()) {
			List<Section> sections = sectionService.findAll();
			uiModel.addAttribute("sections", sections);
			return "dishes/edit";
		}
		saveDish(dish, file);
		uiModel.addAttribute(
				"message",
				new Message("success", messageSource.getMessage("dish_added",
						null, locale)));
		return showMenu(uiModel);
	}

	public void saveDish(Dish dish, Part file) {
		if (file != null) {
			byte[] fileContent = null;
			try {
				InputStream inputStream = file.getInputStream();
				fileContent = IOUtils.toByteArray(inputStream);
				dish.setImage(fileContent);
			} catch (IOException e) {

			}
		}
		dishService.save(dish);
	}

	@RequestMapping(value = "/countrecord", method = RequestMethod.POST)
	public @ResponseBody
	String getTotalSum(@RequestBody String searchCriteria, Model uiModel) {
		String[] request = searchCriteria.split(";");
		List<Dish> dishes = null;
		if (request[0].equalsIgnoreCase("All")) {
			dishes = dishService.findByPrice(Integer.parseInt(request[2]),
					Integer.parseInt(request[3]));
		} else
			dishes = dishService.findBySectionAndPrice(new Section(request[0]),
					Integer.parseInt(request[2]), Integer.parseInt(request[3]));
		dishes = DishesService.findByName(dishes, request[1]);
		return Integer.toString(dishes.size());
	}

	@RequestMapping(value = "/ajaxFind", method = RequestMethod.POST)
	public @ResponseBody
	String ajaxFind(@RequestBody String searchCriteria, Model uiModel) {
		String[] request = searchCriteria.split(";");
		List<Dish> dishes = null;
		if (request[0].equalsIgnoreCase("All")) {
			dishes = dishService.findByPrice(Integer.parseInt(request[2]),
					Integer.parseInt(request[3]));
		} else
			dishes = dishService.findBySectionAndPrice(new Section(request[0]),
					Integer.parseInt(request[2]), Integer.parseInt(request[3]));
		dishes = DishesService.findByName(dishes, request[1]);
		Collection<String> responseCollection = new ArrayList<String>();
		for (int i = 0; i < dishes.size(); i++) {
			responseCollection.add(dishes.get(i).getNameDish());
			responseCollection.add(dishes.get(i).getIngredients());
			responseCollection.add(dishes.get(i).getSection().getNameSection());
			responseCollection.add(dishes.get(i).getCost().toString());
		}

		Gson gson = new Gson();
		return gson.toJson(responseCollection);
	}

}
