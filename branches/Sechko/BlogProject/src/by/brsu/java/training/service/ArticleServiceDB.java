package by.brsu.java.training.service;

import java.util.List;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Tag;

public class ArticleServiceDB implements IArticleService {

	public boolean addArticle(long blogId, Article article) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteArticle(long articleId) {
		// TODO Auto-generated method stub
		return false;
	}

	public Article getArticleById(long articleId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Article getArticleByTags(List<Tag> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	public Article getArticleByText(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Article> getBlogArticles(long blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setArticle(Article article) {
		// TODO Auto-generated method stub

	}

}
