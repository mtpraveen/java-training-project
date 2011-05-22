package voting.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import voting.domain.Answer;

@RooWebScaffold(path = "answers", formBackingObject = Answer.class)
@RequestMapping("/answers")
@Controller
public class AnswerController {
}
