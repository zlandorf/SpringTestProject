package gaming.news.api.models.services;

import gaming.news.api.models.daos.ArticleRepository;
import gaming.news.api.models.daos.CommentRepository;
import gaming.news.api.models.entities.Article;
import gaming.news.api.models.entities.Comment;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleRepository repository;

    @Resource
    private CommentRepository commentRepository;

    @Transactional
    public List<Article> getAll() {
        List<Article> articles = repository.findAll();
        for (Article article : articles) {
            article.setCommentCount(article.getComments().size());
        }
        return articles;
    }

    public Article saveArticle(Article article) {
        commentRepository.save(article.getComments());
        return repository.save(article);
    }

    @Transactional
    public Article get(long id) {
        Article article = repository.findOne(id);
        if (article != null) {
            Hibernate.initialize(article.getComments());
            article.setCommentCount(article.getComments().size());
        }
        return article;
    }
}
