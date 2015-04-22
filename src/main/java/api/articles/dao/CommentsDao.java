package api.articles.dao;

import java.util.List;

import api.articles.Comment;

public interface CommentsDao {
	public List<Comment> getComments(long articleId);
}
