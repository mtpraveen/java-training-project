package by.brsu.java.training.util;

import java.util.Set;

import by.brsu.java.training.entity.Article;
import by.brsu.java.training.entity.Tag;

public class ArticleUtil {
	public static Article copyArticle(Article article) {
		if ( article == null) {
			return null;
		}

		Article articleCopy = new Article(article.getId(), article.getTitle(),
				article.getAuthor(), article.getDate(), article.getText(),
				article.getTags(), article.getComments(), article.isPublished(), article.getBlog());
		return articleCopy;
	}
	
	public static String createTagString(Set<Tag> tags) {
		StringBuffer str = new StringBuffer("");
		for (Tag tag : tags) {
			str.append(tag.getText());
			str.append(", ");
		}
		int startIndex = str.length() - 2;
		if (startIndex < 0 ) {
			startIndex = 0;
		}
		str.delete(startIndex, str.length());
		return str.toString();
	}

}
