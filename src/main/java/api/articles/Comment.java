package api.articles;

public class Comment {
    private long id;
    private long articleId;
    private String comment;

    public Comment(long id, long articleId, String comment) {
        this.id = id;
        this.articleId = articleId;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format("Comment[id=%d, articleId='%s', comment='%s...']\n", getId(), getArticleId(), getComment());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
