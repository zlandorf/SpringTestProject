package gaming.news.api.models.services;

import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.entities.Comment;

import java.util.List;

public interface CommentsService {
    /**
     * Lists all comments belonging to an article with the given id
     * @param articleId
     * @return
     */
    List<Comment> getComments(long articleId);

    /**
     * Adds a comment. The articleId of the comment will be used to determine the article that will own the comment
     * @param comment
     * @return
     * @throws ResourceNotFoundException
     */
    Comment addComment(Comment comment) throws ResourceNotFoundException;
}
