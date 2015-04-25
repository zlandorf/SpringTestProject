package gaming.news.api.controllers;

import java.util.List;

import gaming.news.api.models.entities.Article;
import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.services.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;

    @JsonView(Article.ListView.class)
    @RequestMapping(value = "/articles", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Article> getArticles() {
        return articlesService.getArticles();
    }

    @RequestMapping(value = "/articles/{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Article getArticle(@PathVariable long id) throws ResourceNotFoundException {
        return articlesService.getArticle(id);
    }
}
