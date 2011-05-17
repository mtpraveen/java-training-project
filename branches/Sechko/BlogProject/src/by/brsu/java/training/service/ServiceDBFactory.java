package by.brsu.java.training.service;

public class ServiceDBFactory {
	private static final UserServiceDB USER_SERVICE_DB = new UserServiceDB();
	private static final BlogServiceDB BLOG_SERVICE_DB = new BlogServiceDB();
	private static final ArticleServiceDB ARTICLE_SERVICE_DB = new ArticleServiceDB();
	private static final CommentServiceDB COMMENT_SERVICE_DB = new CommentServiceDB();
	private static final TagServiceDB TAG_SERVICE_DB = new TagServiceDB();
	
	/**
	 * @return the userServiceDb
	 */
	public static UserServiceDB getUserServiceDb() {
		return USER_SERVICE_DB;
	}

	/**
	 * @return the blogServiceDb
	 */
	public static BlogServiceDB getBlogServiceDb() {
		return BLOG_SERVICE_DB;
	}

	/**
	 * @return the articleServiceDb
	 */
	public static ArticleServiceDB getArticleServiceDb() {
		return ARTICLE_SERVICE_DB;
	}

	/**
	 * @return the commentServiceDb
	 */
	public static CommentServiceDB getCommentServiceDb() {
		return COMMENT_SERVICE_DB;
	}
	
	private ServiceDBFactory() {
		
	}
}
