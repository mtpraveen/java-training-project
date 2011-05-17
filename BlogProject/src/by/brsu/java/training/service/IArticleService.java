package by.brsu.java.training.service;

import java.util.List;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Tag;

public interface IArticleService {
	boolean addArticle(long blogId, Article article);
	Article getArticleById(long articleId);
	Article getArticleByText(String text);
	Article getArticleByTags(List<Tag> tags);
	List<Article> getBlogArticles(long blogId);
	void setArticle(Article article);
	boolean deleteArticle(long articleId);
	
}
