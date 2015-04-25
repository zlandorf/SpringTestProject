package gaming.news.api.models.services.impl;

import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.daos.ArticlesDao;
import gaming.news.api.models.entities.Article;
import gaming.news.api.models.services.ArticlesService;
import gaming.news.api.models.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticlesService {

    @Autowired
    ArticlesDao articlesDao;

    @Autowired
    CommentsService commentsService;

    @Override
    public List<Article> getArticles() {
        return articlesDao.getArticles();
    }

    @Override
    public Article getArticle(long id) throws ResourceNotFoundException {
        Article article = articlesDao.getArticle(id);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("[Article id=%s], id"));
        }
        article.setComments(commentsService.getComments(id));
        return article;
    }

    @Override
    public boolean exists(long id) {
        return articlesDao.getArticle(id) != null;
    }
}
