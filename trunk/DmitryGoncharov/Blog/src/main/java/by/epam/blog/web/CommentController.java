package by.epam.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import by.epam.blog.service.CommentServiceImpl;

/**
 * @author Dmitry_Goncharov
 *
 */
@Controller
public class CommentController {
	@Autowired
	private CommentServiceImpl commentService;
	
	
}
