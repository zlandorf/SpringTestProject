package api.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import api.articles.dao.ArticlesDao;
import api.exceptions.ResourceNotFoundException;

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
    public Article getArticle(@PathVariable long id) {
        Article article = articlesDao.getArticle(id);

        if (article == null) {
            throw new ResourceNotFoundException(String.format("Article [id=%d]", id));
        }

        return article;
    }
}
