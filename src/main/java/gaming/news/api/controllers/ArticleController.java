package gaming.news.api.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.entities.Article;
import gaming.news.api.models.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.Format;
import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService service;

    @JsonView(Article.ListView.class)
    @RequestMapping(value = "/articles", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<Article> getArticles() {
        return service.getAll();
    }

    @RequestMapping(value = "/articles/{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Article getArticle(@PathVariable long id) throws ResourceNotFoundException {
        Article article = service.get(id);
        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article with id[%d]", id));
        }
        return article;
    }
}
