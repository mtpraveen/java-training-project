package epam.com.votemanager.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import epam.com.votemanager.domain.Question;
import epam.com.votemanager.domain.Variant;
import epam.com.votemanager.service.IQuestionService;
import epam.com.votemanager.service.IVariantService;
import epam.com.votemanager.validator.QuestionValidator;
import epam.com.votemanager.validator.VariantValidator;

@Controller
public class QuestionController {

	@Autowired
	private IQuestionService questionService;
	@Autowired
	private IVariantService variantService;

	@Autowired
	private QuestionValidator questionValidator;

	@Autowired
	private VariantValidator variantValidator;

	@RequestMapping(value = "/administration/question", method = RequestMethod.GET)
	public String listQuestions(Map<String, Object> map) {

		map.put("question", new Question());
		map.put("questionList", questionService.listQuestion());
		return "question";
	}

	@RequestMapping(value = "/administration/{questionId}/show-variant", method = RequestMethod.GET)
	public String showVariant(@PathVariable("questionId") Integer questionId,
			Map<String, Object> map) {
		map.put("variant", new Variant());
		map.put("variantList", questionService.variants(questionId));
		return "variant";
	}

	@RequestMapping(value = "/administration/question", method = RequestMethod.POST)
	public ModelAndView addQuestion(
			@ModelAttribute("question") Question question,
			BindingResult result, ModelAndView ui) {
		questionValidator.validate(question, result);
		if (result.hasErrors()) {
			ui.setViewName("question");
			ui.addObject("questionList", questionService.listQuestion());
			return ui;
		} else {
			ui.setView(new RedirectView("/votemanager/administration/question"));
			questionService.addQuestion(question);
			return ui;
		}
	}

	@RequestMapping(value = "/administration/delete-question/{questionId}", method = RequestMethod.GET)
	public String deleteQuestion(@PathVariable("questionId") Integer questionId) {

		questionService.removeQuestion(questionId);

		return "redirect:/administration/question";
	}

	@RequestMapping(value = "/administration/{questionId}/delete-variant/{variantId}", method = RequestMethod.GET)
	public String deleteVariant(@PathVariable("questionId") Integer questionId,
			@PathVariable("variantId") Integer variantId) {

		variantService.removeVariant(variantId);

		return "redirect:/administration/{questionId}/show-variant";
	}

	@RequestMapping(value = "/administration/{questionId}/show-variant", method = RequestMethod.POST)
	public ModelAndView addVariant(@ModelAttribute("variant") Variant variant,
			@PathVariable("questionId") Integer questionId,
			BindingResult result, ModelAndView ui) {

		variantValidator.validate(variant, result);
		if (result.hasErrors()) {
			ui.setViewName("variant");
			ui.addObject("variantList", questionService.variants(questionId));
			return ui;
		} else {
			ui.setView(new RedirectView(
					"/votemanager/administration/{questionId}/show-variant"));
			questionService.addVariant(questionId, variant);
			return ui;
		}
	}

	@RequestMapping(value = "/administration/question/{questionId}/add/**", method = RequestMethod.GET)
	public String redirect(@PathVariable("questionId") Integer questionId) {
		return "redirect:/administration/question/{questionId}";
	}

}