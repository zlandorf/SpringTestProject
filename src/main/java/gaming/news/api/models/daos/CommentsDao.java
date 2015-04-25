package gaming.news.api.models.daos;

import java.util.List;

import gaming.news.api.models.entities.Comment;

public interface CommentsDao {
    List<Comment> getComments(long articleId);

    int save(Comment comment);
}
