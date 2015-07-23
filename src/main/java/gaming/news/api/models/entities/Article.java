package gaming.news.api.models.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
public class Article {
    public interface ListView {}

    @JsonView(ListView.class)
    @Id
    @GeneratedValue
    private long id;

    @JsonView(ListView.class)
    private String title;
    @JsonView(ListView.class)
    private String description;
    @JsonView(ListView.class)
    private long commentCount;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Article() {
        comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Article[id=%d, title='%s', description='%s...', comments count='%d']\n", this.id, this.title, this.description.substring(0,  40), this.commentCount);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
