package api.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.articles.dao.ArticlesDao;

@RestController
public class ArticlesController {
	
	@Autowired
	private ArticlesDao articlesDao;
	
	@RequestMapping("/articles")
	public List<Article> getArticles() {
		return articlesDao.getArticles();
	}
}
