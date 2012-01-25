package com.epam.mvc3.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.mvc3.model.Tag;
import com.epam.mvc3.model.Topic;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) 
	{
		logger.info("Welcome home! the client locale is "+ locale.toString());
		logger.info("Running SIMPLE_BLOG");
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("serverTime", formattedDate );
		
		// Check for compatibility
		List listTopic = entityManager.createQuery("select o from Topic o").getResultList(); // delete the last 'o' if an error occurs 
			
		//model.addAttribute("Topics", listTopic);
		
		return new ModelAndView("home", "model", listTopic);
	}
	
	/*
	
	  //
        // GET: /Home/Details/5

        public ViewResult Details(int id)
        {
            Topic topic = db.Topics.Find(id);
            return View(topic);
        }
*/
	
	@RequestMapping(value = "/details/(topicId)")
	public ModelAndView Details(@PathVariable(value="topicId") int id)
	{
		Topic topicById = (Topic) entityManager.createQuery("select o from Topic o where o.id =: myId")
												.setParameter("myId", id)
												.getSingleResult();
			
		return new ModelAndView("/topic/", "model", topicById);
	}
	
	/*
        //
        // GET: /Home/Create

         [Authorize]
        public ActionResult Create()
        {
            return View();
        } 

        //
        // POST: /Home/Create
    */

	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView Create(Locale locale, Model model)
	{
		Topic newTopic = new Topic();
		
		logger.info("HomeControlller: Create");
		
		List<Tag> tagList = newTopic.getTagLict();
		
		Hashtable modelData = new Hashtable();
		modelData.put("topic", newTopic);
		modelData.put("tagList", tagList);
		
		return new ModelAndView("create", modelData);
		
	}
	
	
	/*
        [HttpPost]
        [Authorize]
        public ActionResult Create(Topic topic)
        {
            if (ModelState.IsValid)
            {
                //topic.CommentsCount = 0;
                topic.AuthorID = System.Web.HttpContext.Current.User.Identity.Name;
                //topic.TimeLastUpdated = DateTime.Now;
                db.Topics.Add(topic);
                db.SaveChanges();
                return RedirectToAction("Index");  
            }

            return View(topic);
        
        */
	
	/*
		@RequestMapping(value = "/Create", method = RequestMethod.POST)
		public ModelAndView Create(Locale locale, Model model)
		{
			return new ModelAndView("CreateTopicFromHome");
		}
	*/
	
	/*
        
        //
        // GET: /Home/Edit/5

         [Authorize]
        public ActionResult Edit(int id)
        {
            Topic topic = db.Topics.Find(id);
            return View(topic);
        }

        //
        // POST: /Home/Edit/5

        [HttpPost]
        [Authorize]
        public ActionResult Edit(Topic topic)
        {
            if (ModelState.IsValid)
            {
                db.Entry(topic).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            return View(topic);
        }

        //
        // GET: /Home/Delete/5

         [Authorize]
        public ActionResult Delete(int id)
        {
            Topic topic = db.Topics.Find(id);
            return View(topic);
        }

        //
        // POST: /Home/Delete/5

        [HttpPost, ActionName("Delete")]
        [Authorize]
        public ActionResult DeleteConfirmed(int id)
        {            
            Topic topic = db.Topics.Find(id);
            db.Topics.Remove(topic);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        [HttpPost]
        public ActionResult LogIn(string login, string password)
       {
            LogOnModel newLogonModel = new LogOnModel();
            newLogonModel.UserName = login;
            newLogonModel.Password = password;
            if (Membership.ValidateUser(newLogonModel.UserName, newLogonModel.Password))
            {
                FormsAuthentication.SetAuthCookie(newLogonModel.UserName, newLogonModel.RememberMe);
            }

            // Проверим, существует ли такой пользователь и создадим нового, если нет
            if (!db.UserExists(login))
            {
                // Пользователи, которые существуют ДО запуска ресурса -- являются модераторами
                db.CreateUser(login, BlogRoles.Moderator);

            }

            return RedirectToAction("Index", "Home");
       }

        // Функция  удаляет тему (доступна модераторам, т.к. не отрисовывается для других)
        [HttpPost]
        public ActionResult DeleteTopic(int topicId)
        {
            db.DeleteTopic(topicId);
            return RedirectToAction("Index", "Home");
        }

        protected override void Dispose(bool disposing)
        {
            db.Dispose();
            base.Dispose(disposing);
        }

	 
	*/
	
}
