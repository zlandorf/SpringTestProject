package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import api.articles.Article;
import api.articles.dao.ArticlesDao;

@SpringBootApplication
public class IGNAPIApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IGNAPIApplication.class, args);
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
//	
	@Autowired
	private ArticlesDao articlesDao;

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("drop table articles if exists");
		jdbcTemplate.execute("create table articles (id serial, title varchar(255), description text)");
		
		String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		
		for (int i = 0; i < 5; i++) {
			jdbcTemplate.update("INSERT INTO articles(title, description) values(?,?)", "Article "+i, description);
		}

		System.out.println("Querying all articles");
		List<Article> articles = articlesDao.getArticles();
		for (Article article : articles) {
			System.out.println(article);
		}
	}
}
