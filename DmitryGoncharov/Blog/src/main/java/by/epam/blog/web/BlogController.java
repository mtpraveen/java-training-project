package by.epam.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import by.epam.blog.service.BlogServiceImpl;


/**
 * @author Dmitry_Goncharov
 *
 */

@Controller
public class BlogController {
	@Autowired
	private BlogServiceImpl blogService;

	@RequestMapping(value = "/spisok")
	public String user(Model model) {
		model.addAttribute("blog", blogService.findAllBlogs());
		return "spisok";
	}
	
	@RequestMapping(value = "/killblog")
	public String deleteBlog(@RequestParam("login") String login,@RequestParam("pass") String pass,Model model) {
		if (blogService.findBlogByLogin(login, pass).getId()==0){
			return "redirect:/deleteblog?error=1";
		}else{
			blogService.deleteBlog(blogService.findBlogByLogin(login, pass).getId());
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/deleteblog")
	public String deleteBlog(@RequestParam("error") long errorId,Model model) {
		if (errorId>0){
			model.addAttribute("error", "Неверный логин или пароль, попробуйте снова");
		}
		return "deleteblog";
	}
	
	@RequestMapping(value = "/blog/{blogId}")
	public String blog(@PathVariable("blogId") long blogId, Model model) {
		model.addAttribute("topics", blogService.findBlogById(blogId).getTopics());
		model.addAttribute("user", blogService.findBlogById(blogId).getUser().getName());
		return "blog";
	}
	
	@RequestMapping(value = "/rename",method=RequestMethod.POST)
	public String rename(@RequestParam("login") String login,@RequestParam("pass") String pass,@RequestParam("blogname") String blogname, Model model) {
		if (blogService.findBlogByLogin(login, pass).getId()==0){
			return "redirect:/login?error=1";
		}else{
			model.addAttribute("blognameold", blogService.findBlogByLogin(login, pass).getName());
			blogService.rename(blogService.findBlogByLogin(login, pass), blogname);
			model.addAttribute("blogname", blogname);
			return "rename";
			}
	}
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam("error") long errorId,Model model) {
		if (errorId>0){
			model.addAttribute("error", "Неверный логин или пароль, попробуйте снова");
		}
		return "login";
	}
}
