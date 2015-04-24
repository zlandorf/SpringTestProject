package api.articles.dao;

import java.util.List;

import api.articles.Comment;

public interface CommentsDao {
    List<Comment> getComments(long articleId);
}
