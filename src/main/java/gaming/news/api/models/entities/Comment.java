package gaming.news.api.models.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Comment {
    private long id;
    @NotNull
    private long articleId;
    @NotNull
    @Size(min = 10, max = 256, message = "Comment must be at least 10 characters long")
    private String comment;

    public Comment() {
    }

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
