package api.articles.dao;

import java.util.List;

import api.articles.Article;

public interface ArticlesDao {
    Article getArticle(long id);
    List<Article> getArticles();
}
