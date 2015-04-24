package gaming.news.api.models.daos;

import java.util.List;

import gaming.news.api.models.entities.Article;

public interface ArticlesDao {
    Article getArticle(long id);
    List<Article> getArticles();
}
