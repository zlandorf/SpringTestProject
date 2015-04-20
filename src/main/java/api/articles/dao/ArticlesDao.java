package api.articles.dao;

import java.util.List;

import api.articles.Article;

public interface ArticlesDao {
	public Article getArticle(long id);
	public List<Article> getArticles();
}
