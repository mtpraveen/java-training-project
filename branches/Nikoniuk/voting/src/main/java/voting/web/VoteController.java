package voting.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import voting.domain.Vote;

@RooWebScaffold(path = "votes", formBackingObject = Vote.class)
@RequestMapping("/votes")
@Controller
public class VoteController {
}
