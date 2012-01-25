package com.epam.mvc3.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/topic")
public class TopicController {
	
	/*
	 
	 ContentStorage dbBlog = new ContentStorage();
	*/
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/*

        //
        // GET: /Topic/Details/5

        public ActionResult Details(int id)
        {
            // Найдем в БД нашу тему
            var currentTopic = dbBlog.Topics.Single(Topic => Topic.ID == id);

            // Обновим список комментов
           IEnumerable<Comment> commentsList = Enumerable.Where(dbBlog.Comments, n => n.topicID == id && n.ParentID == 0);
            currentTopic.commentsList = commentsList.ToList();
          
            return View(currentTopic);
        }

         [Authorize]
        public ActionResult AddComment(int topicId)
        {
            var commentedTopic = dbBlog.Topics.Single(Topic => Topic.ID == topicId);
            Comment comment = new Comment
            {
                topicID = topicId,
                topic = commentedTopic
            };
            //ViewBag.Title = commentedTopic.Body;
            return View(comment);
        }

        [HttpPost]
        [Authorize]
        public ActionResult AddComment(Comment comment)
        {
            if (ModelState.IsValid)
            {
                var commentedTopic = dbBlog.Topics.Single(Topic => Topic.ID == comment.topicID);
               
                // Изменим данные, связанные с комментарием
                comment.topic = commentedTopic;
                comment.Date = DateTime.Now;
                dbBlog.Comments.Add(comment);

                // Изменим данные, связанные с самой темой
                commentedTopic.CommentsCount++;
                commentedTopic.TimeLastUpdated = DateTime.Now;

                // Сохраняем в БД
                dbBlog.SaveChanges();

                return RedirectToAction("Details", new { id = comment.topicID} );

            }

            return View(comment);

        }

        //
        // GET: /Topic/Delete/5

         [Authorize]
        public ActionResult Delete(int id)
        {
            return View();
        }

        //
        // POST: /Topic/Delete/5

        [HttpPost]
        [Authorize]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here
 
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

	 
	 */

}
