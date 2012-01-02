package by.epam.blog.dao;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import by.epam.blog.model.Blog;
import by.epam.blog.model.Topic;
import by.epam.blog.model.User;

/**
 * @author Dmitry_Goncharov
 *
 */
public class GoBlogsRepository implements BlogsRepository {
	private HashMap<Long, Blog> blogs;

	public GoBlogsRepository(){
		init();
	}
	private void init(){
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		user1.setName("Dmitry");
		user2.setName("Maksim");
		user3.setName("Daniil");
		blogs=new HashMap<Long, Blog>();
		Blog blog1 = new Blog();
		Blog blog2 = new Blog();
		Blog blog3 = new Blog();
		
		blog1.setId(1L);
		blog1.setAuthor(user1);
		blog1.getTopics().add(new Topic("Статья первая","Привет я первая статья вот так вот!!!"));
		blog1.getTopics().add(new Topic("Статья вторая","Высокая интеграция с другими сервисами гугла. Из загруженных картинок автоматически создаются альбомы в Picasa, можно в один клик мышкой запостить в блог картинку с Picasa или даже документ из Google Docs. Для входа используется тот же логин, что и в остальные сервисы. Гуглофаги порадуются."));
		
		blog2.setId(2L);
		blog2.setAuthor(user2);
		blog2.getTopics().add(new Topic("Про максима","В отличие от многих других блогохостингов, можно получать профит путем размещения контекстной рекламы гугловский AdSense, Яндекс Директ, Бегун Можно публиковать в блог сообщения с внешнего ftp-сервера. Домен — тот, что возможен на этом самом хостинге. Те, кто не осилил ручную правку шаблона, все равно могут изменить мышетыкательным методом набор и расположение гаджетов (так гугл называет фрагменты кода, реализующие некую функциональность, например опрос, календарь или даже крестики-нолики). Искушённые пользователи могут написать свои. Можно прикрутить к блогу собственный домен, причем действие распространяется на все ссылки, а не только на главную страницу, в отличие от ЖЖ."));
		blog2.getTopics().add(new Topic("Про Java","Театральный блог зрителя и студента режиссера драмы о театре и жизни; отзывы на театры и спектакли Минска, белорусское искусство и кино."));
		blog2.getTopics().add(new Topic("Пумпурум","Отчет по SEO оптимизации моего проекта. SEO Секреты, приемы и методы увеличения количества посетителей"));
		
		blog3.setId(3L);
		blog3.getTopics().add(new Topic("Данилка","Мне почему-то кажется что менталитет можно описать тем что каждый о себе думает. Никого не хочу обидеть"));
		blog3.setAuthor(user3);
		
		
		blogs.put(1L, blog1);
		blogs.put(2L, blog2);
		blogs.put(3L, blog3);
		
	}
	
	public Blog createBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	public Blog findBlogById(long id) {
		return blogs.get(id);
	}

	public Blog createBlog(String subject, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Blog> findAllBlogs() {
		// TODO Auto-generated method stub
		return blogs.values();
	}

	public void deleteBlog(long id) {
		// TODO Auto-generated method stub
		
	}
}
