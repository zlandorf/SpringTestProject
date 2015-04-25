package gaming.news.api.models.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import gaming.news.api.models.entities.Article;
import gaming.news.api.models.daos.ArticlesDao;
import gaming.news.api.models.daos.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ArticlesDaoImpl implements ArticlesDao {

    @Autowired
    protected CommentsDao commentsDao;

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    /**{@inheritDoc}*/
    public List<Article> getArticles() {
        return jdbcTemplate.query("select articles.id, articles.title, articles.description, (select count(*) from comments where comments.article_id = articles.id) as article_count from articles", new ArticleMapper());
    }

    @Override
    /**{@inheritDoc}*/
    public Article getArticle(long id) {
        try {
            Article article = jdbcTemplate.queryForObject("select articles.id, articles.title, articles.description, (select count(*) from comments where comments.article_id = articles.id) as article_count from articles where id = ?",  new Object[]{id}, new ArticleMapper());
            article.setComments(commentsDao.getComments(article.getId()));
            return article;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class ArticleMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Article(rs.getLong("id"), rs.getString("title"), rs.getString("description"), rs.getInt("article_count"));
        }
    }
}
