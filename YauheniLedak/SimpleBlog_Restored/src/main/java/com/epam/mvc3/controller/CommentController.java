package com.epam.mvc3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	/*
	public class CommentController : Controller
    {
        ContentStorage dbBlog = new ContentStorage();

        //
        // GET: /Comment/Details/5
        public ActionResult Details(int commentID)
        {
            // Найдем наш коммент в БД
            var currentComment = dbBlog.Comments.Single(Comment => Comment.CommentID == commentID);
            ViewBag.Title = currentComment.Body;

            // Обновим список ПОДЧИНЕННЫХ комментов
            IEnumerable<Comment> commentsList = Enumerable.Where(dbBlog.Comments, n => n.topicID == currentComment.topicID && n.ParentID == commentID);
            currentComment.subCommentsList = commentsList.ToList();

            return View(currentComment);
        }
        */

	
	/*
        //
        // GET: /Comment/Create

         [Authorize]
        public ActionResult CreateNew(int parentId)
        {
            Comment comment = new Comment
            {
                ParentID = parentId,

                //ParentID = parentComment.CommentID,
                // Эти данные не сохраняются в этот момент, хотя в форму комента передаются
                //topicID = parentComment.topicID,
                //Date = DateTime.Now,
                //AuthorID = 1
            };

            return View(comment);
        } 
*/
	
	/*
        //
        // POST: /Comment/Create

        [HttpPost]
        [Authorize]
        public ActionResult CreateNew(Comment comment)
        {
            if (ModelState.IsValid)
            {
                Comment parentComment = dbBlog.Comments.Single(record => record.CommentID == comment.ParentID);
                // Сохраним в БД изменения
                comment.topicID = parentComment.topicID;
                comment.Date = DateTime.Now;
                comment.AuthorID = System.Web.HttpContext.Current.User.Identity.Name;
                dbBlog.Comments.Add(comment);
                dbBlog.SaveChanges();

                // Вернемся к просмотру основного комментария
                return RedirectToAction("Details", new { commentID = comment.ParentID }); 

            }
            return View(comment);

        }        
    }
    */
	
}
