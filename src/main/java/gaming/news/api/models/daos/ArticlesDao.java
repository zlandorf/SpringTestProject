package gaming.news.api.models.daos;

import java.util.List;

import gaming.news.api.models.entities.Article;

public interface ArticlesDao {
    /**
     * Retrieves the article for a given ID
     * @param id the article's ID
     * @return the {@link Article} or null if the article doesn't exist
     */
    Article getArticle(long id);

    /**
     * Retrieves all the articles
     * @return a list of {@link Article}
     */
    List<Article> getArticles();
}
