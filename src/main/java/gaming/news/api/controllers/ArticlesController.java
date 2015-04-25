package gaming.news.api.controllers;

import java.util.List;

import gaming.news.api.models.entities.Article;
import gaming.news.api.models.daos.ArticlesDao;
import gaming.news.api.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ArticlesController {

    @Autowired
    private ArticlesDao articlesDao;

    @JsonView(Article.ListView.class)
    @RequestMapping(value = "/articles", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Article> getArticles() {
        return articlesDao.getArticles();
    }

    @RequestMapping(value = "/articles/{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Article getArticle(@PathVariable long id) throws ResourceNotFoundException {
        Article article = articlesDao.getArticle(id);

        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article [id=%d]", id));
        }

        return article;
    }
}
