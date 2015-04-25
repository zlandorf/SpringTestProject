package gaming.news.api.models.services;

import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.entities.Article;
import gaming.news.api.models.entities.Comment;

import java.util.List;

public interface ArticlesService {
    /**
     * Lists all articles
     * @return
     */
    List<Article> getArticles();

    /**
     * Gets the Article with the given ID
     * @param id
     * @return
     * @throws ResourceNotFoundException if no article has that id
     */
    Article getArticle(long id) throws ResourceNotFoundException;

    /**
     * Tests if an article exists for the given ID
     * @param id
     * @return
     */
    boolean exists(long id);
}
