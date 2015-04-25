package gaming.news.api.models.services.impl;

import gaming.news.api.exceptions.ResourceNotFoundException;
import gaming.news.api.models.daos.CommentsDao;
import gaming.news.api.models.entities.Comment;
import gaming.news.api.models.services.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsDao commentsDao;

    @Override
    public List<Comment> getComments(long articleId) {
        return commentsDao.getComments(articleId);
    }

    @Override
    public Comment addComment(Comment comment) throws ResourceNotFoundException {
        Long createdId = commentsDao.save(comment);
        if (createdId == null) {
            throw new ResourceNotFoundException(String.format("[Article id=%s", comment.getArticleId()));
        }
        comment.setId(createdId);
        return comment;
    }
}
