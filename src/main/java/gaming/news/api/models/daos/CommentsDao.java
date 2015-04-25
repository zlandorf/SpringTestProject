package gaming.news.api.models.daos;

import java.util.List;

import gaming.news.api.models.entities.Comment;

public interface CommentsDao {
    /**
     * Retrieves all comments belonging to the article with the given {@link gaming.news.api.models.entities.Article} ID
     * @param articleId the ID of the {@link gaming.news.api.models.entities.Article}
     * @return a list of {@link Comment} belonging to the article
     */
    List<Comment> getComments(long articleId);

    /**
     *  Saves a {@link Comment} into the database
     * @param comment The comment to insert into the database
     * @return The id of the created comment, or null if no comment was created
     */
    Long save(Comment comment);
}
