package gaming.news.api.models.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import gaming.news.api.models.daos.CommentsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import gaming.news.api.models.entities.Comment;

@Repository
public class CommentsDaoImpl implements CommentsDao {

protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Comment> getComments(long articleId) {
        return jdbcTemplate.query("select id, article_id, comment from comments where article_id = ? ", new Object[]{articleId}, new CommentMapper());
    }

    @Override
    public int save(Comment comment) {
        return jdbcTemplate.update("insert into comments (article_id, comment) select ?,? where exists (select id from articles where id = ?)", comment.getArticleId(), comment.getComment(), comment.getArticleId());
    }

    private static final class CommentMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Comment(rs.getLong("id"), rs.getLong("article_id"), rs.getString("comment"));
        }
    }
}
