package api.articles.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import api.articles.Article;

@Repository
public class ArticlesDaoImpl implements ArticlesDao {
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Article> getArticles() {
		return jdbcTemplate.query(
			"select id, title, description from articles",
			new RowMapper<Article>() {
				@Override
				public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
					return new Article(rs.getLong("id"), rs.getString("title"), rs.getString("description"));
				}
			}
		);
	}
}
