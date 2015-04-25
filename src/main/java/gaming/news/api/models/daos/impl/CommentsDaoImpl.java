package gaming.news.api.models.daos.impl;

import gaming.news.api.models.daos.CommentsDao;
import gaming.news.api.models.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class CommentsDaoImpl implements CommentsDao {

protected JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    /**{@inheritDoc}*/
    public List<Comment> getComments(long articleId) {
        return jdbcTemplate.query("select id, article_id, comment from comments where article_id = ? ", new Object[]{articleId}, new CommentMapper());
    }

    @Override
    /**{@inheritDoc}*/
    public Long save(final Comment comment) {
        final String sql = "insert into comments (article_id, comment) select ?,? where exists (select id from articles where id = ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int row = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, comment.getArticleId());
                ps.setString(2, comment.getComment());
                ps.setLong(3, comment.getArticleId());
                return ps;
            }
        }, keyHolder);

        if (row > 0) {
            return keyHolder.getKey().longValue();
        }
        return null;
    }

    private static final class CommentMapper implements RowMapper<Comment> {
        @Override
        public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Comment(rs.getLong("id"), rs.getLong("article_id"), rs.getString("comment"));
        }
    }
}
